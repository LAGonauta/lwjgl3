/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
package opengl

import org.lwjgl.generator.*
import java.io.*

val NativeClass.capName: String
    get() = if (templateName.startsWith(prefixTemplate)) {
        if (prefix == "GL")
            "OpenGL${templateName.substring(2)}"
        else
            templateName
    } else {
        "${prefixTemplate}_$templateName"
    }

private val CORE_PATTERN = "GL\\d\\dC".toRegex()
val NativeClass.isCore: Boolean
    get() = CORE_PATTERN.matches(templateName)

private const val CAPABILITIES_CLASS = "GLCapabilities"

val GLBinding = Generator.register(object : APIBinding(
    Module.OPENGL,
    CAPABILITIES_CLASS,
    APICapabilities.JNI_CAPABILITIES
) {

    private val classes by lazy { super.getClasses("GL") }

    private val functions by lazy {
        classes
            .asSequence()
            .filter { it.hasNativeFunctions }
            .flatMap { it.functions.asSequence() }
            .filter { !it.has<Reuse>() }
            .toList()
    }

    private val functionOrdinals by lazy {
        functions.asSequence()
            .mapIndexed { index, func -> func.name to index }
            .toMap()
    }

    override fun getFunctionOrdinal(function: Func) = functionOrdinals[function.name]!!

    override fun printCustomJavadoc(writer: PrintWriter, function: Func, documentation: String): Boolean {
        if (function.nativeClass.templateName.startsWith("GL")) {
            writer.printOpenGLJavaDoc(documentation, function.nativeName, function has DeprecatedGL)
            return true
        }
        return false
    }

    private val VECTOR_SUFFIX = "^gl(\\w+?)[ILP]?(?:Matrix)?\\d+(x\\d+)?N?u?(?:[bsifd]|i64)_?v?$".toRegex()
    private val VECTOR_SUFFIX2 = "^gl(?:(Get)n?)?(\\w+?)[ILP]?\\d*N?u?(?:[bsifd]|i64)v$".toRegex()
    private val NAMED = "^gl(\\w+?)?Named([A-Z]\\w*)$".toRegex()

    private fun PrintWriter.printOpenGLJavaDoc(documentation: String, function: String, deprecated: Boolean) {
        val page = VECTOR_SUFFIX.find(function).let {
            if (it == null)
                function
            else
                "gl${it.groupValues[1]}"
        }.let { page ->
            VECTOR_SUFFIX2.find(page).let {
                when {
                    it == null                  -> page
                    page == "glScissorIndexedv" -> "glScissorIndexed"
                    else                        -> "gl${it.groupValues[1]}${it.groupValues[2]}"
                }
            }
        }.let { page ->
            NAMED.find(page).let {
                if (it == null)
                    page
                else
                    "gl${it.groupValues[1]}${it.groupValues[2]}"
            }
        }

        val link = url("http://docs.gl/gl${if (deprecated) "3" else "4"}/$page", "Reference Page")
        val injectedJavaDoc =
            if (deprecated)
                "$link - <em>This function is deprecated and unavailable in the Core profile</em>"
            else
                link

        if (documentation.isEmpty())
            println("$t/** $injectedJavaDoc */")
        else {
            if (documentation.indexOf('\n') == -1) {
                println("$t/**")
                print("$t * ")
                print(documentation.substring("$t/** ".length, documentation.length - " */".length))
            } else {
                print(documentation.substring(0, documentation.length - "\n$t */".length))
            }
            print("\n$t * ")
            print("\n$t * @see $injectedJavaDoc")
            println("\n$t */")
        }
    }

    private val Sequence<Func>.hasDeprecated: Boolean
        get() = this.any { it has DeprecatedGL }

    override fun shouldCheckFunctionAddress(function: Func): Boolean = function.nativeClass.templateName != "GL11" || function has DeprecatedGL

    override fun generateFunctionAddress(writer: PrintWriter, function: Func) {
        writer.println("$t${t}long $FUNCTION_ADDRESS = GL.getICD().${function.name};")
    }

    private val EXTENSION_NAME = "[A-Za-z0-9_]+".toRegex()

    private fun PrintWriter.checkExtensionFunctions(nativeClass: NativeClass) {
        if (nativeClass.isCore) {
            return
        }

        val capName = nativeClass.capName
        val hasDeprecated = nativeClass.functions.hasDeprecated

        print("\n${t}private boolean check_${nativeClass.templateName}(java.util.Set<String> ext")
        if (hasDeprecated) print(", boolean fc")
        println(") {")
        print("$t${t}return ext.contains(\"$capName\") && checkExtension(\"$capName\", ")

        val printPointer = { func: Func ->
            if (func.has<DependsOn>())
                "${func.get<DependsOn>().reference.let { if (EXTENSION_NAME.matches(it)) "ext.contains(\"$it\")" else it }} ? ${func.name} : -1L"
            else
                func.name
        }

        if (hasDeprecated) {
            print("(fc || checkFunctions(")
            nativeClass.printPointers(this, printPointer) { it has DeprecatedGL && !it.has<DependsOn>() }
            print(")) && ")
        }

        print("checkFunctions(")
        if (hasDeprecated)
            nativeClass.printPointers(this, printPointer) { (!it.has(DeprecatedGL) || it.has<DependsOn>()) && !it.has(IgnoreMissing) }
        else
            nativeClass.printPointers(this, printPointer) { !it.has(IgnoreMissing) }
        println("));")
        println("$t}")
    }

    private fun PrintWriter.checkExtensionPresent(core: String, extension: String) {
        println("""${t}private static boolean $extension(java.util.Set<String> ext) { return ext.contains("OpenGL$core") || ext.contains("GL_$extension"); }""")
    }

    init {
        javaImport(
            "org.lwjgl.*",
            "java.lang.reflect.Field",
            "java.util.List",
            "java.util.function.IntFunction",
            "static org.lwjgl.system.APIUtil.*",
            "static org.lwjgl.system.Checks.*",
            "static org.lwjgl.system.MemoryUtil.*"
        )

        documentation = "Defines the capabilities of an OpenGL context."
    }

    override fun PrintWriter.generateJava() {
        generateJavaPreamble()
        println("""public final class $CAPABILITIES_CLASS {

    static final List<Field> ADDRESS_FIELDS = ThreadLocalUtil.getFieldsFromCapabilities($CAPABILITIES_CLASS.class, ${functions.size});
""")

        println("${t}public final long")
        println(functions
            .map(Func::name)
            .joinToString(",\n$t$t", prefix = "$t$t", postfix = ";\n"))

        classes.asSequence()
            .filter { !it.isCore }
            .forEach {
                println(it.getCapabilityJavadoc())
                println("${t}public final boolean ${it.capName};")
            }

        println("""
    /** When true, deprecated functions are not available. */
    public final boolean forwardCompatible;

    /** Off-heap array of the above function addresses. */
    final PointerBuffer addresses;

    $CAPABILITIES_CLASS(FunctionProvider provider, Set<String> ext, boolean fc, IntFunction<PointerBuffer> bufferFactory) {
        forwardCompatible = fc;
""")

        println(functions.joinToString(prefix = "$t$t", separator = "\n$t$t") {
            if (it has DeprecatedGL && !it.has<DependsOn>())
                "${it.name} = getFunctionAddress(fc, provider, ${it.functionAddress});"
            else
                "${it.name} = provider.getFunctionAddress(${it.functionAddress});"
        })

        for (extension in classes) {
            if (extension.isCore) {
                continue
            }
            val capName = extension.capName
            if (extension.hasNativeFunctions) {
                print("\n$t$t$capName = check_${extension.templateName}(ext")
                if (extension.functions.hasDeprecated) print(", fc")
                print(");")
            } else
                print("\n$t$t$capName = ext.contains(\"$capName\");")
        }
        print("""

        addresses = ThreadLocalUtil.getAddressesFromCapabilities(this, ADDRESS_FIELDS, bufferFactory);
    }

    /** Returns the buffer of OpenGL function pointers. */
    public PointerBuffer getAddressBuffer() {
        return addresses;
    }

    private static boolean hasDSA(Set<String> ext) {
        return ext.contains("GL45") || ext.contains("GL_ARB_direct_state_access") || ext.contains("GL_EXT_direct_state_access");
    }

    private static long getFunctionAddress(boolean fc, FunctionProvider provider, String functionName) {
        return fc ? NULL : provider.getFunctionAddress(functionName);
    }

    private static boolean checkExtension(String extension, boolean supported) {
        if (supported) {
            return true;
        }

        apiLog("[GL] " + extension + " was reported as available but an entry point is missing.");
        return false;
    }
""")

        for (extension in classes) {
            if (extension.isCore || !extension.hasNativeFunctions) {
                continue
            }

            checkExtensionFunctions(extension)
        }

        println()

        checkExtensionPresent("30", "ARB_framebuffer_object")
        checkExtensionPresent("30", "ARB_map_buffer_range")
        checkExtensionPresent("30", "ARB_vertex_array_object")
        checkExtensionPresent("31", "ARB_copy_buffer")
        checkExtensionPresent("31", "ARB_texture_buffer_object") // TextureBuffer
        checkExtensionPresent("31", "ARB_uniform_buffer_object") // TransformFeedbackBufferBase, TransformFeedbackBufferRange
        checkExtensionPresent("33", "ARB_instanced_arrays")
        checkExtensionPresent("33", "ARB_sampler_objects")
        checkExtensionPresent("40", "ARB_transform_feedback2")
        checkExtensionPresent("41", "ARB_vertex_attrib_64bit")
        checkExtensionPresent("41", "ARB_separate_shader_objects")
        checkExtensionPresent("42", "ARB_texture_storage")
        checkExtensionPresent("43", "ARB_texture_storage_multisample")
        checkExtensionPresent("43", "ARB_vertex_attrib_binding")
        checkExtensionPresent("43", "ARB_invalidate_subdata")
        checkExtensionPresent("43", "ARB_texture_buffer_range")
        checkExtensionPresent("43", "ARB_clear_buffer_object")
        checkExtensionPresent("43", "ARB_framebuffer_no_attachments")
        checkExtensionPresent("44", "ARB_buffer_storage")
        checkExtensionPresent("44", "ARB_clear_texture")
        checkExtensionPresent("44", "ARB_multi_bind")
        checkExtensionPresent("44", "ARB_query_buffer_object")

        println("\n}")
    }

})

// DSL Extensions

fun String.nativeClassGL(
    templateName: String,
    prefix: String = "GL",
    prefixMethod: String = prefix.toLowerCase(),
    postfix: String = "",
    init: NativeClass.() -> Unit
) = nativeClass(
    Module.OPENGL,
    templateName,
    prefix = prefix,
    prefixMethod = prefixMethod,
    postfix = postfix,
    binding = GLBinding,
    init = {
        init()
        if (!skipNative) {
            nativeImport("opengl.h")
        }
    }
)

private val REGISTRY_PATTERN = "([A-Z]+)_\\w+".toRegex()
val NativeClass.registryLink: String
    get() = url("https://www.khronos.org/registry/OpenGL/extensions/${if (postfix.isNotEmpty()) postfix else {
        (REGISTRY_PATTERN.matchEntire(templateName) ?: throw IllegalStateException("Non-standard extension name: $templateName")).groups[1]!!.value
    }}/$templateName.txt", templateName)

fun NativeClass.registryLink(group: String, name: String): String =
    url("https://www.khronos.org/registry/OpenGL/extensions/$group/${group}_$name.txt", templateName)
fun NativeClass.registryLink(spec: String): String =
    url("https://www.khronos.org/registry/OpenGL/extensions/$postfix/$spec.txt", templateName)

fun registryLinkTo(group: String, name: String): String = "${group}_$name".let {
    url("https://www.khronos.org/registry/OpenGL/extensions/$group/$it.txt", it)
}

val NativeClass.core: String get() = "{@link ${this.className} OpenGL ${this.className[2]}.${this.className[3]}}"
val NativeClass.glx: String get() = "{@link ${this.className} GLX ${this.className[3]}.${this.className[4]}}"
val NativeClass.promoted: String get() = "Promoted to core in ${this.core}."
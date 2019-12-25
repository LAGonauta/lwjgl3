/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.vulkan;

import javax.annotation.*;

import java.nio.*;

import org.lwjgl.*;
import org.lwjgl.system.*;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

/**
 * Structure describing whether the implementation can do depth and stencil image barriers separately.
 * 
 * <h5>Description</h5>
 * 
 * <p>If the {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} structure is included in the {@code pNext} chain of {@link VkPhysicalDeviceFeatures2}, it is filled with values indicating whether the feature is supported. {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} <b>can</b> also be included in the {@code pNext} chain of {@link VkDeviceCreateInfo} to enable the feature.</p>
 * 
 * <h5>Valid Usage (Implicit)</h5>
 * 
 * <ul>
 * <li>{@code sType} <b>must</b> be {@link KHRSeparateDepthStencilLayouts#VK_STRUCTURE_TYPE_PHYSICAL_DEVICE_SEPARATE_DEPTH_STENCIL_LAYOUTS_FEATURES_KHR STRUCTURE_TYPE_PHYSICAL_DEVICE_SEPARATE_DEPTH_STENCIL_LAYOUTS_FEATURES_KHR}</li>
 * </ul>
 * 
 * <h3>Member documentation</h3>
 * 
 * <ul>
 * <li>{@code separateDepthStencilLayouts} &ndash; indicates whether the implementation supports a {@link VkImageMemoryBarrier} for a depth/stencil image with only one of {@link VK10#VK_IMAGE_ASPECT_DEPTH_BIT IMAGE_ASPECT_DEPTH_BIT} or {@link VK10#VK_IMAGE_ASPECT_STENCIL_BIT IMAGE_ASPECT_STENCIL_BIT} set, and whether {@link KHRSeparateDepthStencilLayouts#VK_IMAGE_LAYOUT_DEPTH_ATTACHMENT_OPTIMAL_KHR IMAGE_LAYOUT_DEPTH_ATTACHMENT_OPTIMAL_KHR}, {@link KHRSeparateDepthStencilLayouts#VK_IMAGE_LAYOUT_DEPTH_READ_ONLY_OPTIMAL_KHR IMAGE_LAYOUT_DEPTH_READ_ONLY_OPTIMAL_KHR}, {@link KHRSeparateDepthStencilLayouts#VK_IMAGE_LAYOUT_STENCIL_ATTACHMENT_OPTIMAL_KHR IMAGE_LAYOUT_STENCIL_ATTACHMENT_OPTIMAL_KHR}, or {@link KHRSeparateDepthStencilLayouts#VK_IMAGE_LAYOUT_STENCIL_READ_ONLY_OPTIMAL_KHR IMAGE_LAYOUT_STENCIL_READ_ONLY_OPTIMAL_KHR} can be used.</li>
 * </ul>
 * 
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR {
 *     VkStructureType sType;
 *     void * pNext;
 *     VkBool32 separateDepthStencilLayouts;
 * }</code></pre>
 */
public class VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR extends Struct implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        STYPE,
        PNEXT,
        SEPARATEDEPTHSTENCILLAYOUTS;

    static {
        Layout layout = __struct(
            __member(4),
            __member(POINTER_SIZE),
            __member(4)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        STYPE = layout.offsetof(0);
        PNEXT = layout.offsetof(1);
        SEPARATEDEPTHSTENCILLAYOUTS = layout.offsetof(2);
    }

    /**
     * Creates a {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** Returns the value of the {@code sType} field. */
    @NativeType("VkStructureType")
    public int sType() { return nsType(address()); }
    /** Returns the value of the {@code pNext} field. */
    @NativeType("void *")
    public long pNext() { return npNext(address()); }
    /** Returns the value of the {@code separateDepthStencilLayouts} field. */
    @NativeType("VkBool32")
    public boolean separateDepthStencilLayouts() { return nseparateDepthStencilLayouts(address()) != 0; }

    /** Sets the specified value to the {@code sType} field. */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR sType(@NativeType("VkStructureType") int value) { nsType(address(), value); return this; }
    /** Sets the specified value to the {@code pNext} field. */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR pNext(@NativeType("void *") long value) { npNext(address(), value); return this; }
    /** Sets the specified value to the {@code separateDepthStencilLayouts} field. */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR separateDepthStencilLayouts(@NativeType("VkBool32") boolean value) { nseparateDepthStencilLayouts(address(), value ? 1 : 0); return this; }

    /** Initializes this struct with the specified values. */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR set(
        int sType,
        long pNext,
        boolean separateDepthStencilLayouts
    ) {
        sType(sType);
        pNext(pNext);
        separateDepthStencilLayouts(separateDepthStencilLayouts);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR set(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR malloc() {
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, nmemAllocChecked(SIZEOF));
    }

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR calloc() {
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, nmemCallocChecked(1, SIZEOF));
    }

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated with {@link BufferUtils}. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, memAddress(container), container);
    }

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance for the specified memory address. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR create(long address) {
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, address);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR createSafe(long address) {
        return address == NULL ? null : wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, address);
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer malloc(int capacity) {
        return wrap(Buffer.class, nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer calloc(int capacity) {
        return wrap(Buffer.class, nmemCallocChecked(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated with {@link BufferUtils}.
     *
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer create(int capacity) {
        ByteBuffer container = __create(capacity, SIZEOF);
        return wrap(Buffer.class, memAddress(container), capacity, container);
    }

    /**
     * Create a {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer create(long address, int capacity) {
        return wrap(Buffer.class, address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    @Nullable
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : wrap(Buffer.class, address, capacity);
    }

    // -----------------------------------

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated on the thread-local {@link MemoryStack}. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR mallocStack() {
        return mallocStack(stackGet());
    }

    /** Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero. */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR callocStack() {
        return callocStack(stackGet());
    }

    /**
     * Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR mallocStack(MemoryStack stack) {
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, stack.nmalloc(ALIGNOF, SIZEOF));
    }

    /**
     * Returns a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR callocStack(MemoryStack stack) {
        return wrap(VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.class, stack.ncalloc(ALIGNOF, 1, SIZEOF));
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated on the thread-local {@link MemoryStack}.
     *
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer mallocStack(int capacity) {
        return mallocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated on the thread-local {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer callocStack(int capacity) {
        return callocStack(capacity, stackGet());
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer mallocStack(int capacity, MemoryStack stack) {
        return wrap(Buffer.class, stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
    }

    /**
     * Returns a new {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer callocStack(int capacity, MemoryStack stack) {
        return wrap(Buffer.class, stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #sType}. */
    public static int nsType(long struct) { return UNSAFE.getInt(null, struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.STYPE); }
    /** Unsafe version of {@link #pNext}. */
    public static long npNext(long struct) { return memGetAddress(struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.PNEXT); }
    /** Unsafe version of {@link #separateDepthStencilLayouts}. */
    public static int nseparateDepthStencilLayouts(long struct) { return UNSAFE.getInt(null, struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.SEPARATEDEPTHSTENCILLAYOUTS); }

    /** Unsafe version of {@link #sType(int) sType}. */
    public static void nsType(long struct, int value) { UNSAFE.putInt(null, struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.STYPE, value); }
    /** Unsafe version of {@link #pNext(long) pNext}. */
    public static void npNext(long struct, long value) { memPutAddress(struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.PNEXT, value); }
    /** Unsafe version of {@link #separateDepthStencilLayouts(boolean) separateDepthStencilLayouts}. */
    public static void nseparateDepthStencilLayouts(long struct, int value) { UNSAFE.putInt(null, struct + VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.SEPARATEDEPTHSTENCILLAYOUTS, value); }

    // -----------------------------------

    /** An array of {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR} structs. */
    public static class Buffer extends StructBuffer<VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR, Buffer> implements NativeResource {

        private static final VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR ELEMENT_FACTORY = VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.create(-1L);

        /**
         * Creates a new {@code VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer} instance backed by the specified container.
         *
         * Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR#SIZEOF}, and its mark will be undefined.
         *
         * <p>The created buffer instance holds a strong reference to the container object.</p>
         */
        public Buffer(ByteBuffer container) {
            super(container, container.remaining() / SIZEOF);
        }

        public Buffer(long address, int cap) {
            super(address, null, -1, 0, cap, cap);
        }

        Buffer(long address, @Nullable ByteBuffer container, int mark, int pos, int lim, int cap) {
            super(address, container, mark, pos, lim, cap);
        }

        @Override
        protected Buffer self() {
            return this;
        }

        @Override
        protected VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** Returns the value of the {@code sType} field. */
        @NativeType("VkStructureType")
        public int sType() { return VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.nsType(address()); }
        /** Returns the value of the {@code pNext} field. */
        @NativeType("void *")
        public long pNext() { return VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.npNext(address()); }
        /** Returns the value of the {@code separateDepthStencilLayouts} field. */
        @NativeType("VkBool32")
        public boolean separateDepthStencilLayouts() { return VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.nseparateDepthStencilLayouts(address()) != 0; }

        /** Sets the specified value to the {@code sType} field. */
        public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer sType(@NativeType("VkStructureType") int value) { VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.nsType(address(), value); return this; }
        /** Sets the specified value to the {@code pNext} field. */
        public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer pNext(@NativeType("void *") long value) { VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.npNext(address(), value); return this; }
        /** Sets the specified value to the {@code separateDepthStencilLayouts} field. */
        public VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.Buffer separateDepthStencilLayouts(@NativeType("VkBool32") boolean value) { VkPhysicalDeviceSeparateDepthStencilLayoutsFeaturesKHR.nseparateDepthStencilLayouts(address(), value ? 1 : 0); return this; }

    }

}
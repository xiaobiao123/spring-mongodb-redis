package cn.springmvc.collection;

import java.util.*;

//继承AbstractList，支持泛型
public class ArrayListSource<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    private static final long serialVersionUID = 8683452581122892189L;

    //默认初始化大小
    private static final int DEFAULT_CAPACITY = 10;

    //空表
    private static final Object[] EMPTY_ELEMENTDATA = {};

    //内部实现为数组
    private transient Object[] elementData;

    //元素数目
    private int size;

    //初始化一个initialCapacity大小的ArrayList
    public ArrayListSource(int initialCapacity) {
        super();//父类的构造函数本身没做什么事情
        if (initialCapacity < 0)//检查是否合法
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        this.elementData = new Object[initialCapacity];
    }

    //初始化一个空的ArrayList
    public ArrayListSource() {
        super();
        this.elementData = EMPTY_ELEMENTDATA;//数组为空
    }

    //按c里面的元素来初始化一个ArrayList
    public ArrayListSource(Collection<? extends E> c) {
        //c中的元素转换为数组,elementData的类型为Object[],此处不需要类型转换    
        elementData = c.toArray();
        size = elementData.length;
        if (elementData.getClass() != Object[].class)//确保elementData数组是object类型
            elementData = Arrays.copyOf(elementData, size, Object[].class);
    }

    //相当于压缩ArrayList，比如ArrayList有10个空间，现在只存储了5个元素，
    // 通过该方法将ArrayList的空间变为5个
    public void trimToSize() {
        modCount++;//修改次数+1
        if (size < elementData.length) {
            elementData = Arrays.copyOf(elementData, size);
        }
    }

    //申请minCapacity个空间
    public void ensureCapacity(int minCapacity) {
        //获取表默认的初始容量,空表为0，其他的默认为DEFAULT_CAPACITY
        int minExpand = (elementData != EMPTY_ELEMENTDATA) ? 0 : DEFAULT_CAPACITY;
        //只有minCapacity大于默认初始容量，才申请新的大小的空间
        if (minCapacity > minExpand) {
            ensureExplicitCapacity(minCapacity);//执行扩容
        }
    }

    //申请minCapacity个空间
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == EMPTY_ELEMENTDATA) {
            //minCapacity取DEFAULT_CAPACITY和minCapacity的最大值
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        ensureExplicitCapacity(minCapacity);//执行扩容
    }

    //执行扩容，容量为minCapacity
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;//修改次数+1

        //只有minCapacity大于默认初始容量，才执行扩容
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);//执行扩容,容量为minCapacity
    }

    //JVM支持大小为MAX_ARRAY_SIZE容量的数组
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    //执行扩容
    private void grow(int minCapacity) {
        //记录老的容量值
        int oldCapacity = elementData.length;
        //新的容量按老容量的1.5倍来扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            //如果申请的容量比目前容量的1.5倍还要大
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        //增加新的空间，新增加空间的长度为newCapacity,原有的元素放到新空间的前面
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) //溢出了
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    //返回元素数目
    public int size() {
        return size;
    }

    //判断ArrayList是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //判断是否包含值为o的元数
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //定位第一个值为o的元素
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    //定位最后一个值为o的元素
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = size - 1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

    //执行浅拷贝
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ArrayListSource<E> v = (ArrayListSource<E>) super.clone();
            //只执行数组中的引用拷贝，引用指向的内存一致
            v.elementData = Arrays.copyOf(elementData, size);
            v.modCount = 0;
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    //转换为数组
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    //转换为a类型的数组
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    //定位第index个元素
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];//O(1)操作
    }

    //获取第index个元素
    public E get(int index) {
        rangeCheck(index);

        return elementData(index);
    }

    //设置第index个元素的值为element
    public E set(int index, E element) {
        rangeCheck(index);

        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    //尾部添加元素e
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }

    //第index个位置添加元素element
    public void add(int index, E element) {
        rangeCheckForAdd(index);

        ensureCapacityInternal(size + 1);  // Increments modCount!!
        System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
        elementData[index] = element;
        size++;
    }

    //删除第index个元素
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }

    //删除值为o的第一个元素
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    //快速删除第index个元素，即不返回删除的元素值
    private void fastRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null; // clear to let GC do its work
    }

    //将ArrayList置空
    public void clear() {
        modCount++;

        //保证GC可以工作
        for (int i = 0; i < size; i++)
            elementData[i] = null;

        size = 0;
    }

    //将c里面的所有元素都添加到当前表的尾部
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    //将c里面的所有元素都添加到第index个位置之后
    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);

        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.arraycopy(elementData, index, elementData, index + numNew,
                    numMoved);//执行拷贝

        System.arraycopy(a, 0, elementData, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    //删除formIndex和toIndex之间的元素
    protected void removeRange(int fromIndex, int toIndex) {
        modCount++;
        int numMoved = size - toIndex;
        System.arraycopy(elementData, toIndex, elementData, fromIndex,
                numMoved);

        // clear to let GC do its work
        int newSize = size - (toIndex - fromIndex);
        for (int i = newSize; i < size; i++) {
            elementData[i] = null;
        }
        size = newSize;
    }

    //index范围检查
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    //index范围检查
    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    //删除所有c里面不包含的元素
    public boolean removeAll(Collection<?> c) {
        return batchRemove(c, false);
    }

    //删除所有c里面包含的元素
    public boolean retainAll(Collection<?> c) {
        return batchRemove(c, true);
    }

    //执行批量删除
    private boolean batchRemove(Collection<?> c, boolean complement) {
        final Object[] elementData = this.elementData;
        int r = 0, w = 0;
        boolean modified = false;
        try {
            for (; r < size; r++)
                if (c.contains(elementData[r]) == complement)
                    elementData[w++] = elementData[r];
        } finally {
            // Preserve behavioral compatibility with AbstractCollection,
            // even if c.contains() throws.
            if (r != size) {
                System.arraycopy(elementData, r,
                        elementData, w,
                        size - r);
                w += size - r;
            }
            if (w != size) {
                // clear to let GC do its work
                for (int i = w; i < size; i++)
                    elementData[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    //序列化
    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        // Write out element count, and any hidden stuff
        int expectedModCount = modCount;
        s.defaultWriteObject();

        // Write out size as capacity for behavioural compatibility with clone()
        s.writeInt(size);

        // Write out all elements in the proper order.
        for (int i = 0; i < size; i++) {
            s.writeObject(elementData[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    //反序列化
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        elementData = EMPTY_ELEMENTDATA;

        // Read in size, and any hidden stuff
        s.defaultReadObject();

        // Read in capacity
        s.readInt(); // ignored

        if (size > 0) {
            // be like clone(), allocate array based upon size not capacity
            ensureCapacityInternal(size);

            Object[] a = elementData;
            // Read in all elements in the proper order.
            for (int i = 0; i < size; i++) {
                a[i] = s.readObject();
            }
        }
    }

    //返回一个从index开始的双向迭代器
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);
        return new ListItr(index);
    }

    //返回一个双向迭代器
    public ListIterator<E> listIterator() {
        return new ListItr(0);
    }

    //返回迭代器
    public Iterator<E> iterator() {
        return new Itr();
    }

    //私有内部类，从第0个元素开始的单向迭代器
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
        int expectedModCount = modCount;

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayListSource.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayListSource.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    //私有内部类，从第index元素开始的双向迭代器
    private class ListItr extends Itr implements ListIterator<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWarnings("unchecked")
        public E previous() {
            checkForComodification();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = ArrayListSource.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
                ArrayListSource.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            checkForComodification();

            try {
                int i = cursor;
                ArrayListSource.this.add(i, e);
                cursor = i + 1;
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

}
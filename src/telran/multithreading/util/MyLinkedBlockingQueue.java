package telran.multithreading.util;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyLinkedBlockingQueue <E> implements BlockingQueue<E> {
	
	ReentrantReadWriteLock rwl;
	ReentrantLock lock;
 	LinkedList<E> list;
	int limit;
	E e;
	Condition waitingProducer;
	
	public MyLinkedBlockingQueue(int limit) {
		this.limit = limit;
		this.list = new LinkedList<>();
		this.rwl = new ReentrantReadWriteLock();
		this.lock = new ReentrantLock();
	}
	
	public MyLinkedBlockingQueue() {
		this.limit = Integer.MAX_VALUE;
		this.list = new LinkedList<>();
		this.rwl = new ReentrantReadWriteLock();
	}


	
	@Override
	public E remove() {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			e = list.remove();
			return e;
		}finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
		
	}

	@Override
	public E poll() {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.poll();
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
	}
		 
	}

	@Override
	public E element() {
		rwl.readLock().lock();
		lock.lock();
		try {
			try {
				e = list.element();
			}catch (NoSuchElementException e) {
			};
		return e;
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public E peek() {
		rwl.readLock().lock();
		lock.lock();
		try {
			return list.peek();
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public int size() {
		rwl.readLock().lock();
		lock.lock();
		try {
			return list.size();
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean isEmpty() {
		rwl.readLock().lock();
		lock.lock();
		try {
		return list.isEmpty();
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public Iterator<E> iterator() {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.iterator();
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public Object[] toArray() {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.toArray();
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
		
	}

	@Override
	public <T> T[] toArray(T[] a) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.toArray(a);
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		rwl.readLock().lock();
		lock.lock();
		try {
			return list.containsAll(c);	
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.addAll(c);
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
		
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.removeAll(c);
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			return list.retainAll(c);
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public void clear() {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			list.clear();
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean add(E e) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		boolean res = false;
		try {
			if (list.size() < limit) {
				res = list.add(e);
			} else {
				throw new IllegalStateException();
			}
			return res;
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean offer(E e) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		boolean res = false;
		try {
			if (list.size() < limit) {
				res = list.offer(e);
			}
			return res;
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public void put(E e) throws InterruptedException {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
			boolean running = true;
			while (running) {
				if (list.size() < limit) {
					list.add(e);
					running = false;
				} else {
					try {
						list.wait(60000);
					} catch (InterruptedException ex) {
						};
				}
			}
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}

	}

	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		boolean res = false;
		try {
			if (list.size() < limit) {
				res = list.offer(e);
			} else {
				try {
					list.wait(TimeUnit.MILLISECONDS.toMillis(timeout));
				} catch (InterruptedException ex) {
					};
			}
			return res;
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public E take() throws InterruptedException {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		E res = null;
		try {
			boolean running = true;
				while (running) {
					res = this.peek();
					if (this.remove(res)) {
						running = false;
					 } else {
					try {
						list.wait(60000);
					} catch (InterruptedException ex) {
						};
				}
			}
			return res;
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		E res = null;
		try {
			boolean running = true;
				while (running) {
					res = this.peek();
					if (this.remove(res)) {
						running = false;
					 } else {
					try {
						list.wait(TimeUnit.MILLISECONDS.toMillis(timeout));
					} catch (InterruptedException ex) {
						};
				}
			}
			return res;
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public int remainingCapacity() {
		rwl.readLock().lock();
		lock.lock();
		try {
			return limit - list.size();
		} finally {
			rwl.readLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean remove(Object o) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
		return list.remove(o);
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public boolean contains(Object o) {
		rwl.readLock().lock();
		rwl.writeLock().lock();
		lock.lock();
		try {
		return list.contains(o);
		} finally {
			rwl.readLock().unlock();
			rwl.writeLock().unlock();
			lock.unlock();
		}
	}

	@Override
	public int drainTo(Collection<? super E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		throw new UnsupportedOperationException();
	}

}

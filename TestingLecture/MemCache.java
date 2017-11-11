package bg.uni.sofia.fmi.mjt.cache;

import java.util.HashMap;
import java.time.LocalDateTime;
import java.util.Map;

public class MemCache<K, V> implements Cache<K, V> {
	private long capacity;
	private Map<K, V> cache;
	private Map<K, LocalDateTime> expirationDate;

	public MemCache(long capacity) {
		this.capacity = capacity;
		Integer i = (int) (long) this.capacity;
		this.cache = new HashMap<>(i);
		this.expirationDate = new HashMap<>(i);

	}

	public MemCache() {
		this.capacity = 10000L;
		Integer i = (int) (long) this.capacity;
		this.cache = new HashMap<>(i);
		this.expirationDate = new HashMap<>(i);
	}

	public V get(K key) {
		for (Map.Entry<K, V> entry : cache.entrySet()) {
			LocalDateTime current = LocalDateTime.now();
			if (entry.getKey().equals(key) && expirationDate.get(key).isBefore(current)) {
				return entry.getValue();
			} else if (entry.getKey().equals(key) && expirationDate.get(key).isAfter(current)) {
				cache.remove(entry.getKey());
				return null;
			}
		}
		return null;
	}

	public void set(K key, V value, LocalDateTime expiresAt) throws CapacityExceededException {

		if (!cache.containsKey(key) && cache.size() == this.capacity) {
			throw new CapacityExceededException();
		}

		if (key != null && value != null) {
			if (cache.containsKey(key)) {
				cache.put(key, value);
				expirationDate.put(key, expiresAt);
			}
			if (cache.size() == this.capacity) {
				LocalDateTime current = LocalDateTime.now();
				for (Map.Entry<K, V> entry : cache.entrySet()) {

					if (expirationDate.get(entry.getKey()).isBefore(current)) {
						cache.remove(entry.getKey());
						cache.put(key, value);
						expirationDate.put(key, expiresAt);
						break;
					}
				}
			}
		}
	}

	public LocalDateTime getExpiration(K key) {
		return expirationDate.get(key);
	}

	/**
	 * Removes the item associated with the specified key from the cache. Returns
	 * true, if an item with the specified key was found and false otherwise.
	 */
	public boolean remove(K key) {
		if (cache.containsKey(key)) {
			cache.remove(key);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of all actual (expired or not) items stored currently in
	 * the cache.
	 */
	public long size() {
		return cache.size();
	}

	public void clear() {
		cache.clear();
		expirationDate.clear();
	}

	/**
	 * Return the percentage of the successful hits for this cache. It is a
	 * double-precision number in the interval [0.0, 1.0] and is equal to the ratio
	 * of get(K, V) calls that returned a non-null value versus the calls that
	 * returned null. If there is not a single successful hit, the hit rate is 0.0.
	 * If there is at least one successful hit and the missed hits are zero, the hit
	 * rate is 1.0
	 */
	public double getHitRate() {
		double hits=0;
		double misses=0;
		for (Map.Entry<K, V> entry : cache.entrySet()) {
			if (entry.getValue() == null) {
				misses++;
			} else {
				hits++;
			}
		}
		if(hits==0) {
			return 0.0;
		}
		if(hits>0&&misses==0) {
			return 1;
		}
		return hits/misses;
	}
}

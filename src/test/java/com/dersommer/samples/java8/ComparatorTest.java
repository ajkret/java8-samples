package com.dersommer.samples.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class ComparatorTest extends TestCase {

	@Test
	// Old style sort, with Collections
	public void test001_SortListOldSchool() {
		List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

		Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

		Assert.assertEquals(4, names.size());
		Assert.assertEquals("George", names.get(0));
	}

	// List interface now implements the default method 'sort'
	public void test002_ListSort() {
		List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

		names.sort((s1, s2) -> s1.compareTo(s2));

		Assert.assertEquals(4, names.size());
		Assert.assertEquals("George", names.get(0));
	}

	// Comparator now has a factory
	public void test003_SortUsingComparingFactory() {
		List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

		names.sort(Comparator.comparing(name -> name));

		Assert.assertEquals(4, names.size());
		Assert.assertEquals("George", names.get(0));
	}

	// Comparator has ready to use Comparators
	public void test004_SortUsingNaturalOrder() {
		List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

		names.sort(Comparator.naturalOrder());

		Assert.assertEquals(4, names.size());
		Assert.assertEquals("George", names.get(0));
	}

	class Rockstar {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	// Comparator has ready to use Comparators
	public void test005_SortUsingReferenceMethod() {
		List<String> names = Arrays.asList("John", "Ringo", "Paul", "George");

		/*
		 * What is the difference between map and flatMap?
		 * 
		 * http://stackoverflow.com/a/26684710/2315849
		 * 
		 * Both map and flatMap can be applied to a Stream<T> and they both
		 * return a Stream<R>. The difference is that the map operation produces
		 * one output value for each input value, whereas the flatMap operation
		 * produces an arbitrary number (zero or more) values for each input
		 * value.
		 * 
		 * This is reflected in the arguments to each operation.
		 * 
		 * The map operation takes a Function, which is called for each value in
		 * the input stream and produces one result value, which is sent to the
		 * output stream.
		 * 
		 * The flatMap operation takes a function that conceptually wants to
		 * consume one value and produce an arbitrary number of values. However,
		 * in Java, it's cumbersome for a method to return an arbitrary number
		 * of values, since methods can return only zero or one value. One could
		 * imagine an API where the mapper function for flatMap takes a value
		 * and returns an array or a List of values, which are then sent to the
		 * output. Given that this is the streams library, a particularly apt
		 * way to represent an arbitrary number of return values is for the
		 * mapper function itself to return a stream! The values from the stream
		 * returned by the mapper are drained from the stream and are passed to
		 * the output stream. The "clumps" of values returned by each call to
		 * the mapper function are not distinguished at all in the output
		 * stream, thus the output is said to have been "flattened."
		 * 
		 * Typical use is for the mapper function of flatMap to return
		 * Stream.empty() if it wants to send zero values, or something like
		 * Stream.of(a, b, c) if it wants to return several values. But of
		 * course any stream can be returned.
		 */

		// Build another list with objects
		List<Rockstar> rockstars = names.stream().map(s -> {
			Rockstar dto = new Rockstar();
			dto.setName(s);
			return dto;
		}).collect(Collectors.toList());

		// Order that list
		rockstars.sort(Comparator.comparing(Rockstar::getName));

		Assert.assertEquals(4, rockstars.size());
		Assert.assertEquals("George", rockstars.get(0).getName());
	}
}
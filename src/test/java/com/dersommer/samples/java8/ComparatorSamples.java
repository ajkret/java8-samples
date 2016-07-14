package com.dersommer.samples.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ComparatorSamples {
	
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


}
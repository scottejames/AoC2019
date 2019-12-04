package com.scottejames.aoc2019;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DayOneMassTest {

    @Test
    public void testSampleData(){

       int massOfTwelve =  DayOneMass.calculateMass(12);
       int massOfForteen = DayOneMass.calculateMass(14);
       int massOfThousand = DayOneMass.calculateMass(1969);
       int massOfLarge = DayOneMass.calculateMass(100756);

       assertEquals(massOfTwelve,2);
       assertEquals(massOfForteen, 2);
       assertEquals(massOfThousand, 654);
       assertEquals(massOfLarge, 33583);
       assertNotEquals(massOfLarge, 555);
    }
}

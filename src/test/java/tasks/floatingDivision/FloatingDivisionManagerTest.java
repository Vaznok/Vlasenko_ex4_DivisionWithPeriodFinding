package tasks.floatingDivision;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static tasks.floatingDivision.FloatingDivisionManager.*;
import static tasks.floatingDivision.FloatingDivisionManager.makeDivision;

public class FloatingDivisionManagerTest {

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentDivisorZero_IllegalArgumentExceptionThrown() {
        makeDivision(542, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentNumberLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(-2, 23);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentDivisorLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(542, -56);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentsNumberAndDivisorLessThanZero_IllegalArgumentExceptionThrown() {
        makeDivision(-2, -23);
    }
    @Test(expected = IllegalArgumentException.class)
    public void makeDivision_ArgumentsNumberAndDivisorEqualsZero_IllegalArgumentExceptionThrown() {
        makeDivision(0, 0);
    }

    @Test
    public void makeDivision_ArgumentNumberZero_CorrectResult() {
        makeDivision(0, 23);
        assertThat(getPartialNums(), is(Arrays.asList()));
        assertThat(getNearestDivisorNums(), is(Arrays.asList()));
        assertThat(getNumRemains(), is(Arrays.asList()));
        assertThat(getResult(), is("0"));
    }

    @Test
    public void makeDivision_ArgumentNumberFiveDigitsDivisorOneDigit_CorrectResult() {
        makeDivision(78459, 4);
        assertThat(getPartialNums(), is(Arrays.asList(7, 38, 24, 5, 19, 30, 20)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4, 36, 24, 4, 16, 28, 20)));
        assertThat(getNumRemains(), is(Arrays.asList(3, 2, 0, 1, 3, 2, 0)));
        assertThat(getResult(), is("19614.75"));
    }

    @Test
    public void makeDivision_ArgumentNumberFiveDigitsDivisorOneDigitInitialPeriodTen_CorrectResult() {
        makeDivision(78459, 4, 10);
        assertThat(getPartialNums(), is(Arrays.asList(7, 38, 24, 5, 19, 30, 20)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(4, 36, 24, 4, 16, 28, 20)));
        assertThat(getNumRemains(), is(Arrays.asList(3, 2, 0, 1, 3, 2, 0)));
        assertThat(getResult(), is("19614.75"));
    }
    @Test
    public void makeDivision_ArgumentNumberFourDigitsDivisorOneDigitWithRecurPeriod_CorrectResult() {
        makeDivision(1000, 3);
        assertThat(getPartialNums(), is(Arrays.asList(10, 10, 10, 10)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(9, 9, 9, 9)));
        assertThat(getNumRemains(), is(Arrays.asList(1, 1, 1, 1)));
        assertThat(getResult(), is("333.(3)"));
    }

    @Test
    public void makeDivision_ArgumentNumberFourDigitsDivisorOneDigitWithRecurPeriodInitialPeriodTen_CorrectResult() {
        makeDivision(1000, 3, 10);
        assertThat(getPartialNums(), is(Arrays.asList(10, 10, 10, 10)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(9, 9, 9, 9)));
        assertThat(getNumRemains(), is(Arrays.asList(1, 1, 1, 1)));
        assertThat(getResult(), is("333.(3)"));
    }

    @Test
    public void makeDivision_ArgumentNumberOneDigitDivisorTwoDigitsWithRecurPeriod_CorrectResult() {
        makeDivision(7, 12);
        assertThat(getPartialNums(), is(Arrays.asList(70, 100, 40)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(60, 96, 36)));
        assertThat(getNumRemains(), is(Arrays.asList(10, 4, 4)));
        assertThat(getResult(), is("0.58(3)"));
    }

    @Test
    public void makeDivision_ArgumentNumberOneDigitDivisorTwoDigitsWithRecurPeriodInitialPeriodTen_CorrectResult() {
        makeDivision(7, 12, 10);
        assertThat(getPartialNums(), is(Arrays.asList(70, 100, 40)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(60, 96, 36)));
        assertThat(getNumRemains(), is(Arrays.asList(10, 4, 4)));
        assertThat(getResult(), is("0.58(3)"));
    }

    @Test
    public void makeDivision_ArgumentsDivisorHasSameDigitsAsNumberWithRecurPeriod_CorrectResult() {
        makeDivision(25, 39);
        assertThat(getPartialNums(), is(Arrays.asList(250, 160, 40, 100, 220)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(234, 156, 39, 78, 195)));
        assertThat(getNumRemains(), is(Arrays.asList(16, 4, 1, 22, 25)));
        assertThat(getResult(), is("0.(641025)"));
    }

    @Test
    public void makeDivision_ArgumentsDivisorHasSameDigitsAsNumberWithRecurPeriodInitialPeriodTen_CorrectResult() {
        makeDivision(25, 39, 10);
        assertThat(getPartialNums(), is(Arrays.asList(250, 160, 40, 100, 220)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(234, 156, 39, 78, 195)));
        assertThat(getNumRemains(), is(Arrays.asList(16, 4, 1, 22, 25)));
        assertThat(getResult(), is("0.(641025)"));
    }

    @Test
    public void makeDivision_ArgumentNumberOneDigitDivisorTwoDigitsWithRecurPeriodInitialPeriodThree_CorrectResult() {
        makeDivision(2, 99, 3);
        assertThat(getPartialNums(), is(Arrays.asList(200)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(198)));
        assertThat(getNumRemains(), is(Arrays.asList(2)));
        assertThat(getResult(), is("0.(02)"));
    }

    @Test
    public void makeDivision_ArgumentsDivisorHasSameDigitsAsNumberWithRecurPeriodInitialPeriodNine_CorrectResult() {
        makeDivision(17, 11, 9);
        assertThat(getPartialNums(), is(Arrays.asList(17, 60, 50)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(11, 55, 44)));
        assertThat(getNumRemains(), is(Arrays.asList(6, 5, 6)));
        assertThat(getResult(), is("1.(54)"));
    }

    @Test
    public void makeDivision_ArgumentsNumberOneDigitDivisorThreeDigitsWithRecurPeriod_CorrectResult() {
        makeDivision(1, 999);
        assertThat(getPartialNums(), is(Arrays.asList(1000)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(999)));
        assertThat(getNumRemains(), is(Arrays.asList(1)));
        assertThat(getResult(), is("0.(001)"));
    }
    @Test
    public void makeDivision_ArgumentsNumberOneDigitDivisorSevenDigitsWithRecurPeriod_CorrectResult() {
        makeDivision(4, 9999999);
        assertThat(getPartialNums(), is(Arrays.asList(40000000)));
        assertThat(getNearestDivisorNums(), is(Arrays.asList(39999996)));
        assertThat(getNumRemains(), is(Arrays.asList(4)));
        assertThat(getResult(), is("0.(0000004)"));
    }
}

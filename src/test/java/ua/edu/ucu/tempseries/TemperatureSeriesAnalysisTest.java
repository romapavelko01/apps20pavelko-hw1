package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Assert;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries1 = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis1 = new TemperatureSeriesAnalysis(temperatureSeries1);
        double expResult1 = -1.0;
        double actualResult1 = seriesAnalysis1.average();

        double[] temperatureSeries2 = {-273.0};
        TemperatureSeriesAnalysis seriesAnalysis2 = new TemperatureSeriesAnalysis(temperatureSeries2);
        double expResult2 = -273.0;
        double actualResult2 = seriesAnalysis2.average();

        // compare expected result with actual result
        assertEquals(expResult1, actualResult1, 0.00001);
        assertEquals(expResult2, actualResult2, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAverageWithBadValues() {
        double[] temperatureSeries = {0.5, -273.0, -300.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(temperatureSeries);
        analysis.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;
        double actualResult = seriesAnalysis.average();

        double[] series = {36.6, 0.1, -273.0, -3.5};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        double expected = -59.95;
        double actual = analysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);
        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testDeviationWithOneElement() {
        double[] mySeries1 = {0.0};
        TemperatureSeriesAnalysis myAnalysis1 = new TemperatureSeriesAnalysis(mySeries1);
        double expResult1 = 0.0;
        double result1 = myAnalysis1.deviation();

        assertEquals(expResult1, result1, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        double[] series = {};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        analysis.deviation();
    }

    @Test(expected = InputMismatchException.class)
    public void testDeviationWithBadValues() {
        double[] series = {0.0, 273.0, -273.0, -274};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        analysis.deviation();
    }

    @Test
    public void testDeviation() {
        double[] series = {36.6, 273.0, -273.0, 274.0};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        double expResult = 224.363;
        double result = analysis.deviation();

        double[] anotherSeries = {0.3, -0.1, 0.003, 0.95, 0.101};
        TemperatureSeriesAnalysis anotherAnalysis = new TemperatureSeriesAnalysis(anotherSeries);
        double anotherExpected = 0.373;
        double anotherActual = anotherAnalysis.deviation();

        assertEquals(anotherExpected, anotherActual, 0.001);
        assertEquals(expResult, result, 0.001);

    }

    @Test
    public void testGetTemperaturesWithOneElement() {
        double[] series = {10.1};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        double[] received = analysis.getTemperatures();

        Assert.assertArrayEquals(series, received, 0.0001);
    }

    @Test
    public void testGetTemperaturesDefault() {
        TemperatureSeriesAnalysis newAn = new TemperatureSeriesAnalysis();
        double[] myArr = {36.6, 34.5, 94.2};
        for (double myTemp : myArr) {
            newAn.addTemps(myTemp);
        }
        double[] actual = newAn.getTemperatures();
        double[] notWanted = {36.6, 34.5, 94.2, 0.0, 0.0};

        Assert.assertArrayEquals(myArr, actual, 0.0001);
        assertNotSame(notWanted, actual);
    }

    @Test
    public void testMinWithOneElement() {
        double[] series = {35.9};
        TemperatureSeriesAnalysis analysis = new TemperatureSeriesAnalysis(series);
        double expectedMin = series[0];
        double actualMin = analysis.min();

        assertEquals(expectedMin, actualMin, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        double[] mySeries = {};
        TemperatureSeriesAnalysis myAn = new TemperatureSeriesAnalysis(mySeries);
        myAn.min();
    }

    @Test(expected = InputMismatchException.class)
    public void testMinWithBadValues() {
        double[] anotherSer= {-273.0, 0.8, 98.4, -280.0};
        TemperatureSeriesAnalysis anotherAn = new TemperatureSeriesAnalysis(anotherSer);
        anotherAn.min();
    }

    @Test
    public void testMin() {
        double[] finalMinSer = {0.8, -0.8, 24.4, -0.2, 30.3};
        TemperatureSeriesAnalysis finalMinAn = new TemperatureSeriesAnalysis(finalMinSer);
        double expectedMin = -0.8;
        double actualMin = finalMinAn.min();

        assertEquals(expectedMin, actualMin, 0.0001);
    }

    @Test
    public void testMaxWithOneElement() {
        double[] maxSerOne = {0.0};
        TemperatureSeriesAnalysis maxOneAn = new TemperatureSeriesAnalysis(maxSerOne);
        double expectedOneMax = 0.0;
        double actualOneMax = maxOneAn.max();

        assertEquals(expectedOneMax, actualOneMax, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        double[] maxEmpty = {};
        TemperatureSeriesAnalysis maxEmptyAn = new TemperatureSeriesAnalysis(maxEmpty);

        maxEmptyAn.max();
    }

    @Test(expected = InputMismatchException.class)
    public void testMaxWithBadValues() {
        double[] maxBad = {-274.9, 0.9, 23.9};
        TemperatureSeriesAnalysis maxBadAn = new TemperatureSeriesAnalysis(maxBad);

        maxBadAn.max();
    }

    @Test
    public void testMax() {
        double[] maxFinal = {-10.5, 34.8, 34.7, 29.0, -34.8};
        TemperatureSeriesAnalysis maxFinalAn = new TemperatureSeriesAnalysis(maxFinal);
        double expected = 34.8;
        double actual = maxFinalAn.max();

        assertEquals(expected, actual, 0.00001);
    }

    @Test
    public void testClosestToZeroWithOneElement() {
        double[] serClosZeroOne = {0.1};
        TemperatureSeriesAnalysis closZeroOneAn = new TemperatureSeriesAnalysis(serClosZeroOne);
        double closZerOne = serClosZeroOne[0];
        double actualClosZerOne = closZeroOneAn.findTempClosestToZero();

        assertEquals(closZerOne, actualClosZerOne, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testClosestToZeroBadValues() {
        double[] serClosZeroBad = {0.0, 289.9, -289.5, -273.0, 34.4, 0.4};
        TemperatureSeriesAnalysis closZeroBadAn = new TemperatureSeriesAnalysis(serClosZeroBad);

        closZeroBadAn.findTempClosestToZero();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToZeroEmptyArray() {
        double[] emptySer = {};
        TemperatureSeriesAnalysis closZeroEmpty = new TemperatureSeriesAnalysis(emptySer);

        closZeroEmpty.findTempClosestToZero();
    }

    @Test
    public void testClosestToZero() {
        double[] myValuesOne = {-2.0, 2.0};
        TemperatureSeriesAnalysis anOne = new TemperatureSeriesAnalysis(myValuesOne);
        double expectedValueOne = 2.0;
        double actualValueOne = anOne.findTempClosestToZero();

        double[] myValuesTwo = {0.3, 0.9, 36.6, -0.2};
        TemperatureSeriesAnalysis anTwo = new TemperatureSeriesAnalysis(myValuesTwo);
        double expectedValueTwo = -0.2;
        double actualValueTwo = anTwo.findTempClosestToZero();

        assertEquals(expectedValueOne, actualValueOne, 0.0001);
        assertEquals(expectedValueTwo, actualValueTwo, 0.0001);
    }

    @Test
    public void testClosestToValueOneElement() {
        double[] myArrOne = {0.1};
        TemperatureSeriesAnalysis anOneClosVal = new TemperatureSeriesAnalysis(myArrOne);
        double expClosValOne = myArrOne[0];
        double actualClosValOne = anOneClosVal.findTempClosestToValue(-274.0);

        assertEquals(expClosValOne, actualClosValOne, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClosestToValueEmpty() {
        double[] myArrTwo = {};
        TemperatureSeriesAnalysis anTwoClosVal = new TemperatureSeriesAnalysis(myArrTwo);
        anTwoClosVal.findTempClosestToValue(0.5);
        TemperatureSeriesAnalysis myAnTwo = new TemperatureSeriesAnalysis();
        myAnTwo.findTempClosestToValue(12.1);
    }

    @Test(expected = InputMismatchException.class)
    public void testClosestToValueBad() {
        double[] myArrThree = {0.0, -300.0, 300.0};
        TemperatureSeriesAnalysis anThreeClosVal = new TemperatureSeriesAnalysis(myArrThree);
        anThreeClosVal.findTempClosestToValue(-150.1);
    }

    @Test
    public void testClosestToValue() {
        double[] myArrFour = {0.1, -0.1, 55.9, 94.3};
        TemperatureSeriesAnalysis anFourClosVal = new TemperatureSeriesAnalysis(myArrFour);
        double tempValOne = 0.0;
        double tempValTwo = 60.3;
        double expOne = 0.1;
        double expTwo = 55.9;
        double actOne = anFourClosVal.findTempClosestToValue(tempValOne);
        double actTwo = anFourClosVal.findTempClosestToValue(tempValTwo);

        assertEquals(expOne, actOne, 0.00001);
        assertEquals(expTwo, actTwo, 0.00001);
    }

    @Test
    public void testFindLessThanTemp() {
        double[] lessArr = {-240.4, -250.2, -34.0, 0.5, 19.5, 26.9, 28.5, 36.1, 40.2};
        TemperatureSeriesAnalysis lessAn = new TemperatureSeriesAnalysis(lessArr);
        double[] expLessArrOne = {-240.4, -250.2, -34.0};
        double[] actLessArrOne = lessAn.findTempsLessThen(0.0);
        Assert.assertArrayEquals(expLessArrOne, actLessArrOne, 0.00001);

        double[] expLessArrTwo = {};
        double[] actLessArrTwo = lessAn.findTempsLessThen(-260.0);
        Assert.assertArrayEquals(expLessArrTwo, actLessArrTwo, 0.00001);
    }

    @Test
    public void testFindGreaterThanTemp() {
        double[] greaterArr = {23.0, 19.4, 19.4, 123.5, -19.4, 35.3};
        TemperatureSeriesAnalysis greaterAn = new TemperatureSeriesAnalysis(greaterArr);
        double tempValueOne = 19.4;
        double[] expGreaterArrOne = {23.0, 123.5, 35.3};
        double[] actGreaterArrOne = greaterAn.findTempsGreaterThen(tempValueOne);
        Assert.assertArrayEquals(expGreaterArrOne, actGreaterArrOne, 0.0001);

        double tempValueTwo = 124.0;
        double[] expGreaterArrTwo = {};
        double[] actGreaterArrTwo = greaterAn.findTempsGreaterThen(tempValueTwo);
        Assert.assertArrayEquals(expGreaterArrTwo, actGreaterArrTwo, 0.0001);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsBadValues() {
        double[] seriesOne = {220.0, 120.0};
        TemperatureSeriesAnalysis anOne = new TemperatureSeriesAnalysis(seriesOne);
        TemperatureSeriesAnalysis anTwo = new TemperatureSeriesAnalysis(seriesOne);
        anOne.addTemps(-34.6, 283.9, -283.8);
        int mySizeOne = anOne.addTemps(35.6);
        int mySizeTwo = anTwo.addTemps(35.6);
        assertEquals(mySizeOne, mySizeTwo, 0.0001);

        double[] anOneArr = anOne.getTemperatures();
        double[] anTwoArr = anTwo.getTemperatures();
        Assert.assertArrayEquals(anOneArr, anTwoArr, 0.0001);

        anOne.addTemps(-300.0);
        double[] anOneNewArr = anOne.getTemperatures();
        Assert.assertArrayEquals(anTwoArr, anOneNewArr, 0.0001);

        TemperatureSeriesAnalysis finalAn = new TemperatureSeriesAnalysis();
        int expDefSize = 0;
        double[] expFinalArray = finalAn.getTemperatures();
        int actDefSize = finalAn.addTemps(280.0, 23.3, -280.0);
        double[] actFinalArray = finalAn.getTemperatures();

        assertEquals(expDefSize, actDefSize, 0.0001);

        Assert.assertArrayEquals(expFinalArray, actFinalArray, 0.0001);
    }

    @Test
    public void testAddTemps() {
        double[] myTemps = {22.4, 25.9, 24.1};
        TemperatureSeriesAnalysis myTempsAn = new TemperatureSeriesAnalysis(myTemps);
        int newSize = myTempsAn.addTemps(36.1, 30.4, 31.9);
        int expectedSize = 6;
        double[] expValues = {22.4, 25.9, 24.1, 36.1, 30.4, 31.9};
        double[] actValues = myTempsAn.getTemperatures();
        Assert.assertArrayEquals(expValues, actValues, 0.0001);
        assertEquals(expectedSize, newSize, 0.001);

        double[] tempsOne = {0.4, 0.6};
        for (double value: tempsOne) {
            newSize = myTempsAn.addTemps(value);
        }
        int expSizeChanged = 8;
        double[] expChangedValues = {22.4, 25.9, 24.1, 36.1, 30.4, 31.9, 0.4, 0.6};
        double[] actChangedValues = myTempsAn.getTemperatures();
        Assert.assertArrayEquals(expChangedValues, actChangedValues, 0.0001);
        assertEquals(expSizeChanged, newSize, 0.0001);

        TemperatureSeriesAnalysis emptyAn = new TemperatureSeriesAnalysis();
        int expEmptyChangedSize = 2;
        int actEmptyChangedSize = emptyAn.addTemps(tempsOne);
        double[] expEmptyChangedTemps = tempsOne.clone();
        double[] actEmptyChangedTemps = emptyAn.getTemperatures();

        assertEquals(expEmptyChangedSize, actEmptyChangedSize, 0.0001);
        Assert.assertArrayEquals(expEmptyChangedTemps, actEmptyChangedTemps, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        TemperatureSeriesAnalysis myBadTempAn = new TemperatureSeriesAnalysis();
        TempSummaryStatistics myBadStats = new TempSummaryStatistics(myBadTempAn);
    }

    @Test
    public void testSummaryStatistics() {
        double[] myTempArr = {9.6, 5.0, 1.3, 4.8};
        TemperatureSeriesAnalysis myTempAn = new TemperatureSeriesAnalysis(myTempArr);
        TempSummaryStatistics myTempStats = new TempSummaryStatistics(myTempAn);
        double expAverage = myTempAn.average();
        double expDeviation = myTempAn.deviation();
        double expMax = myTempAn.max();
        double expMin = myTempAn.min();

        double actAverage = myTempStats.getAvgTemp();
        double actDeviation = myTempStats.getDevTemp();
        double actMax = myTempStats.getMaxTemp();
        double actMin = myTempStats.getMinTemp();

        assertEquals(expAverage, actAverage, 0.0001);
        assertEquals(expDeviation, actDeviation, 0.0001);
        assertEquals(expMax, actMax, 0.0001);
        assertEquals(expMin, actMin, 0.0001);

        double[] anotherArr = {2.0, 3.5};
        TemperatureSeriesAnalysis myAn = new TemperatureSeriesAnalysis(anotherArr);
        TempSummaryStatistics anStats = new TempSummaryStatistics(myAn);
        TemperatureSeriesAnalysis actAn = anStats.getMyAnalysis();
        double expAvOne = myAn.average();
        double expDevOne = myAn.deviation();
        double expMaxOne = myAn.max();
        double expMinOne = myAn.min();

        double actAvOne = actAn.average();
        double actDevOne = actAn.deviation();
        double actMaxOne = actAn.max();
        double actMinOne = actAn.min();

        assertEquals(expAvOne, actAvOne, 0.0001);
        assertEquals(expDevOne, actDevOne, 0.0001);
        assertEquals(expMaxOne, actMaxOne, 0.0001);
        assertEquals(expMinOne, actMinOne, 0.0001);

        actAn.addTemps(3.9, -3.4);
        double newAv = actAn.average();
        double newDev = actAn.deviation();
        double newMax = actAn.max();
        double newMin = actAn.min();

        assertNotEquals(actAvOne, newAv);
        assertNotEquals(actDevOne, newDev);
        assertNotEquals(actMaxOne, newMax);
        assertNotEquals(actMinOne, newMin);

        double[] finalSeries = {3.5, 6.9, 8.9};
        TemperatureSeriesAnalysis finalAn = new TemperatureSeriesAnalysis(finalSeries);
        TempSummaryStatistics finalStats = finalAn.summaryStatistics();

        double finalExpAv = finalAn.average();
        double finalExpDev = finalAn.deviation();
        double finalExpMax = finalAn.max();
        double finalExpMin = finalAn.min();

        double finalActAv = finalStats.getAvgTemp();
        double finalActDev = finalStats.getDevTemp();
        double finalActMax = finalStats.getMaxTemp();
        double finalActMin = finalStats.getMinTemp();

        assertEquals(finalExpAv, finalActAv, 0.0001);
        assertEquals(finalExpDev, finalActDev, 0.0001);
        assertEquals(finalExpMax, finalActMax, 0.0001);
        assertEquals(finalExpMin, finalActMin, 0.0001);
    }
}

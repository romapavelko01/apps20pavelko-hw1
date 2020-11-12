package ua.edu.ucu.tempseries;
import java.lang.Math;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int defaultsize = 1;
    private static final int dangertemp = -273;
    private double[] temperatures;
    private int currSize;
    private int vacantSize;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[defaultsize];
        currSize = 0;
        vacantSize = defaultsize;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        currSize = temperatureSeries.length;
        temperatures = new double[currSize];
        for (int j = 0; j < currSize; j++) {
            if (temperatureSeries[j] < dangertemp) {
                temperatures = new double[currSize];
                throw new InputMismatchException();
            }
            temperatures[j] = temperatureSeries[j];

        }
        temperatures = temperatureSeries;
        currSize = temperatures.length;
        vacantSize = 0;
    }

    public double[] getTemperatures() {
        double[] myArr = new double[currSize];
        int j = 0;
        while (j < currSize) {
            myArr[j] = temperatures[j];
            j += 1;
        }
        return myArr;
    }

    public double average() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double mySum = 0;
        for (int counter = 0; counter < currSize; counter++) {
            mySum += temperatures[counter];
        }
        return mySum/currSize;

    }

    public double deviation() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double average = average();
        double sumOfSquares = 0;
        for (int i = 0; i < currSize; i++) {
            sumOfSquares += Math.pow(temperatures[i] - average, 2.0);
        }
        return Math.pow(sumOfSquares/currSize, 0.5);
    }

    public double min() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double myMin = temperatures[0];
        int index = 1;
        while (index < currSize) {
            if (temperatures[index] < myMin) {
                myMin = temperatures[index];
            }
            index += 1;
        }
        return myMin;
    }

    public double max() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double myMax = temperatures[0];
        int index = 1;
        while (index < currSize) {
            if (temperatures[index] > myMax) {
                myMax = temperatures[index];
            }
            index += 1;
        }
        return myMax;
    }

    public double findTempClosestToZero() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double closestToZero = temperatures[0];
        int index = 1;
        while (index < currSize) {
            if (Math.abs(temperatures[index]) < Math.abs(closestToZero)
                    || (temperatures[index] > 0
                            && closestToZero < 0
                            && Math.abs(temperatures[index]) == Math.abs(closestToZero))) {
                closestToZero = temperatures[index];
            }
            index += 1;
        }
        return closestToZero;
    }

    public double findTempClosestToValue(double tempValue) {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double[] closestTempArr = new double[] {temperatures[0], Math.abs(temperatures[0] - tempValue)};
        int index = 1;
        while (index < currSize) {
            double myVal = Math.abs(temperatures[index] - tempValue);
            if (closestTempArr[1] > myVal
                    || (closestTempArr[0] < 0 && temperatures[index] > 0 && myVal == closestTempArr[1])) {
                closestTempArr = new double[] {temperatures[index], myVal};
            }
            index += 1;
        }
        return closestTempArr[0];

    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        for (int k = 0; k < currSize; k++) {
            if (temperatures[k] < tempValue) {
                counter += 1;
            }
        }
        double[] myArray = new double[counter];
        if (counter > 0) {
            int indexer = 0;
            for (int s = 0; s < currSize; s++) {
                if (temperatures[s] < tempValue) {
                    myArray[indexer] = temperatures[s];
                    indexer += 1;
                }
            }
        }
        return myArray;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        for (int d = 0; d < currSize; d++) {
            if (temperatures[d] > tempValue) {
                counter += 1;
            }
        }
        double[] finalArr = new double[counter];
        if (counter > 0) {
            int indexer = 0;
            for (int c = 0; c < currSize; c++) {
                if (temperatures[c] > tempValue) {
                    finalArr[indexer] = temperatures[c];
                    indexer += 1;
                }
            }
        }
        return finalArr;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this);
    }

    public int addTemps(double... temps) {
        double[] oldTemperatures = temperatures;
        if (currSize == 0) {
            int auxSize = defaultsize;
            while (auxSize < temps.length) {
                auxSize *= 2;
                vacantSize *= 2;
            }
            currSize = temps.length;
            temperatures = new double[auxSize];
            for (int j = 0; j < currSize; j++) {
                if (temps[j] >= dangertemp) {
                    temperatures[j] = temps[j];
                    vacantSize -= 1;
                }
                else {
                    temperatures = oldTemperatures;
                    vacantSize = defaultsize;
                    currSize = 0;
                    throw new InputMismatchException();
                }
            }
        }
        else {
            int auxSize = currSize + vacantSize;
            while (vacantSize < temps.length) {
                auxSize *= 2;
                vacantSize = auxSize - currSize;
            }
            double[] myTemps = new double[auxSize];
            for (int index = 0; index < currSize; index++) {
                myTemps[index] = temperatures[index];
            }
            for (int j = 0; j < temps.length; j++) {
                if (temps[j] >= dangertemp) {
                    myTemps[j + currSize] = temps[j];
                    vacantSize -= 1;
                } else {
                    temperatures = oldTemperatures;
                    throw new InputMismatchException();
                }
            }
            temperatures = myTemps.clone();
            currSize = auxSize - vacantSize;
        }
        return currSize;
    }
}

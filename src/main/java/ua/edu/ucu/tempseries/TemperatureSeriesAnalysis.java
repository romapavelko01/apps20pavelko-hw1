package ua.edu.ucu.tempseries;
import java.lang.Math;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final int defaultSize = 1;
    private static final int dangerTemp = -273;
    private double[] temperatures;
    private int currSize;
    private int vacantSize;

    public TemperatureSeriesAnalysis() {
        temperatures = new double[defaultSize];
        currSize = 0;
        vacantSize = defaultSize;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        currSize = temperatureSeries.length;
        temperatures = new double[currSize];
        for (int j = 0; j < currSize; j++) {
            if (temperatureSeries[j] < dangerTemp) {
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
        if (currSize == 0){
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
        double Average = average();
        double SumOfSquares = 0;
        for (int i = 0; i < currSize; i++) {
            SumOfSquares += Math.pow(temperatures[i] - Average, 2.0);
        }
        return Math.pow(SumOfSquares/currSize, 0.5);
    }

    public double min() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double MyMin = temperatures[0];
        int Index = 1;
        while (Index < currSize) {
            if (temperatures[Index] < MyMin) {
                MyMin = temperatures[Index];
            }
            Index += 1;
        }
        return MyMin;
    }

    public double max() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double MyMax = temperatures[0];
        int Index = 1;
        while (Index < currSize) {
            if (temperatures[Index] > MyMax) {
                MyMax = temperatures[Index];
            }
            Index += 1;
        }
        return MyMax;
    }

    public double findTempClosestToZero() {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double ClosestToZero = temperatures[0];
        int Index = 1;
        while (Index < currSize) {
            if (Math.abs(temperatures[Index]) < Math.abs(ClosestToZero) ||
                    (temperatures[Index] > 0
                            && ClosestToZero < 0
                            && Math.abs(temperatures[Index]) == Math.abs(ClosestToZero))){
                ClosestToZero = temperatures[Index];
            }
            Index += 1;
        }
        return ClosestToZero;
    }

    public double findTempClosestToValue(double tempValue) {
        if (currSize == 0) {
            throw new IllegalArgumentException();
        }
        double[] ClosestTempArr = new double[] {temperatures[0], Math.abs(temperatures[0] - tempValue)};
        int Index = 1;
        while (Index < currSize) {
            double myVal = Math.abs(temperatures[Index] - tempValue);
            if (ClosestTempArr[1] > myVal ||
                    (ClosestTempArr[0] < 0 && temperatures[Index] > 0 && myVal == ClosestTempArr[1])) {
                ClosestTempArr = new double[] {temperatures[Index], myVal};
            }
            Index += 1;
        }
        return ClosestTempArr[0];

    }

    public double[] findTempsLessThen(double tempValue) {
        int counter = 0;
        for (int k = 0; k < currSize; k++) {
            if (temperatures[k] < tempValue) {
                counter += 1;
            }
        }
        double[] MyArray = new double[counter];
        if (counter > 0) {
            int indexer = 0;
            for (int s = 0; s < currSize; s++) {
                if (temperatures[s] < tempValue) {
                    MyArray[indexer] = temperatures[s];
                    indexer += 1;
                }
            }
        }
        return MyArray;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int counter = 0;
        for (int d = 0; d < currSize; d++) {
            if (temperatures[d] > tempValue) {
                counter += 1;
            }
        }
        double[] Final = new double[counter];
        if (counter > 0) {
            int indexer = 0;
            for (int c = 0; c < currSize; c++) {
                if (temperatures[c] > tempValue) {
                    Final[indexer] = temperatures[c];
                    indexer += 1;
                }
            }
        }
        return Final;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this);
    }

    public int addTemps(double... temps) {
        double[] oldTemperatures = temperatures;
        if (currSize == 0) {
            int auxSize = defaultSize;
            while (auxSize < temps.length) {
                auxSize *= 2;
                vacantSize *= 2;
            }
            currSize = temps.length;
            temperatures = new double[auxSize];
            for (int j = 0; j < currSize; j++) {
                if (temps[j] >= dangerTemp) {
                    temperatures[j] = temps[j];
                    vacantSize -= 1;
                }
                else {
                    temperatures = oldTemperatures;
                    vacantSize = defaultSize;
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
                if (temps[j] >= dangerTemp) {
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

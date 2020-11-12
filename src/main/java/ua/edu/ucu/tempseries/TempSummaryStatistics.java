package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private double avgTemp, devTemp, minTemp, maxTemp;
    private TemperatureSeriesAnalysis myAnalysis;
    public TempSummaryStatistics(TemperatureSeriesAnalysis analysis) {
        myAnalysis = new TemperatureSeriesAnalysis(analysis.getTemperatures());
        avgTemp = analysis.average();
        devTemp = analysis.deviation();
        minTemp = analysis.min();
        maxTemp = analysis.max();
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public TemperatureSeriesAnalysis getMyAnalysis() {
        return myAnalysis;
    }
}

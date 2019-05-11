### Summary
* Generates series of normal and anomalous data
* Visualize series data in browser

### API Examples
Generate arbitrary series data
```$xslt
// declare new SeriesGenerator with a 'normal' range of 30-40 and minute interval
SeriesGenerator seriesGenerator = new SeriesGenerator(new Range(30, 40), MINUTE)
		.generateSeries(52)    // generate 52 'normal' datum
		.generateAnomalies(12) // generate 12 anomalous datum
		.generateSeries(37)    // generate 37 'normal' datum
		
String series = seriesGenerator.getSeries(); // collect series to String
```

Visualize series in browser
```$xslt
visualizeSeries(series);
```

### Todo
* Improve tests
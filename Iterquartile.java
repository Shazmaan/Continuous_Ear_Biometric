package weka.api;
import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.InterquartileRange;
import weka.filters.unsupervised.instance.RemoveWithValues;





public class Iterquartile {
    public static void main(String args[]) throws Exception{
        DataSource source = new DataSource("/home/shageenth/AndroidStudioProjects/spambase.arff");
        Instances dataset = source.getDataSet();
        InterquartileRange interquartile = new InterquartileRange();

        interquartile.setInputFormat(dataset);
        Instances dataset2 = Filter.useFilter(dataset,interquartile);

        String[] options = new String[4];
        options[0] = "-C"; options[1] = "22"; options[2] = "-L"; options[3] = "last";
        RemoveWithValues remove1 = new RemoveWithValues();
        remove1.setOptions(options);
        remove1.setInputFormat(dataset2);
        Instances dataset3 = Filter.useFilter(dataset2,remove1);
        options[0] = "-C"; options[1] = "23"; options[2] = "-L"; options[3] = "last";
        RemoveWithValues remove2 = new RemoveWithValues();
        remove2.setOptions(options);
        remove2.setInputFormat(dataset3);
        Instances newData = Filter.useFilter(dataset3,remove2);


        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("/home/shageenth/AndroidStudioProjects/spambase3.arff"));
        saver.writeBatch();;


    }
}

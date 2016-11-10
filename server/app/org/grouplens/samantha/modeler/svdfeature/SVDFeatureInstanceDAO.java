package org.grouplens.samantha.modeler.svdfeature;

import org.grouplens.samantha.modeler.featurizer.Feature;
import org.grouplens.samantha.modeler.common.LearningData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="http://www.grouplens.org">GroupLens Research</a>
 */
public class SVDFeatureInstanceDAO implements LearningData {
    private final File sourceFile;
    private BufferedReader reader;

    public SVDFeatureInstanceDAO(File sourceFile)
            throws FileNotFoundException {
        this.sourceFile = sourceFile;
        reader = new BufferedReader(new FileReader(this.sourceFile));
    }

    public SVDFeatureInstance getLearningInstance() {
        try {
            String line = reader.readLine();
            if (line == null) {
                return null;
            } else {
                List<Feature> gfeas = new ArrayList<>();
                List<Feature> ufeas = new ArrayList<>();
                List<Feature> ifeas = new ArrayList<>();
                SVDFeatureInstance ins = new SVDFeatureInstance(gfeas, ufeas, ifeas, 0.0, 0.0);
                SVDFeatureUtilities.parseInstanceFromString(line, ins);
                return ins;
            }
        } catch (IOException e) {
            //TODO: add logging
            return null;
        }
    }

    public void startNewIteration() {
        try {
            reader.close();
            reader = new BufferedReader(new FileReader(sourceFile));
        } catch (IOException e) {
            //TODO: add logging
        }
    }
}
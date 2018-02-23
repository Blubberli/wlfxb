/**
 *
 */
package eu.clarin.weblicht.wlfxb.tclayers.test;

import eu.clarin.weblicht.wlfxb.tc.api.ReferencesLayer;
import eu.clarin.weblicht.wlfxb.tc.xb.ReferencesLayerStored;
import eu.clarin.weblicht.wlfxb.test.utils.TestUtils;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

/**
 * @author Yana Panchenko
 *
 */
public class ReferencesTest {

    private static final String INPUT = "/data/tc-refs/layer-input.xml";
    private static final String INPUT_ANY_ATTRIBUTES = "/data/tc-refs/layer-inputAnyAtt.xml";

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @Test
    public void testReadAndWriteBack() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(INPUT);
        OutputStream os = new FileOutputStream(testFolder.newFile("layer-output.xml"));

        ReferencesLayer layer = TestUtils.read(ReferencesLayerStored.class, is);
        System.out.println(layer);
        TestUtils.write(layer, os);

        is.close();
        os.close();

        Assert.assertEquals("BART", layer.getTypetagset());
        Assert.assertEquals("TuebaDZ", layer.getReltagset());
        Assert.assertEquals(true, layer.hasExternalReferences());
        Assert.assertEquals("Wikipedia", layer.getExternalReferenceSource());
        Assert.assertEquals(2, layer.size());
        Assert.assertEquals("http://de.wikipedia.org/wiki/Wahre_Finnen", layer.getReferencedEntity(0).getExternalId());
        Assert.assertEquals(2, layer.getReferencedEntity(0).getReferences().length);
        Assert.assertEquals("pro.per3", layer.getReferencedEntity(0).getReferences()[0].getType());
        Assert.assertEquals("cataphoric", layer.getReferencedEntity(0).getReferences()[0].getRelation());
    }

    @Test
    public void testReadAndWriteBackAnyAttributes() throws Exception {

        InputStream is = this.getClass().getResourceAsStream(INPUT_ANY_ATTRIBUTES);
        OutputStream os = new FileOutputStream(testFolder.newFile("layer-output.xml"));

        ReferencesLayer layer = TestUtils.read(ReferencesLayerStored.class, is);
        System.out.println(layer);
        TestUtils.write(layer, os);

        is.close();
        os.close();

        Integer index = 0;
        for (String anyAttribute : layer.getReferencedEntity(0).getReferences()[0].getExtraAtrributes().keySet()) {
            if (index == 0) {
                Assert.assertEquals("baseForm", anyAttribute);
                Assert.assertEquals("baseFormWordRef", layer.getReferencedEntity(0).getReferences()[0].getExtraAtrributes().get(anyAttribute));
            }
            if (index == 1) {
                Assert.assertEquals("alterForm", anyAttribute);
                Assert.assertEquals("alterFormWordRef", layer.getReferencedEntity(0).getReferences()[0].getExtraAtrributes().get(anyAttribute));
            }

            index++;
        }

    }
}

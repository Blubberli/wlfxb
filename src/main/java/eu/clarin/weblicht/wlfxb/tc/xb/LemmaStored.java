/**
 *
 */
package eu.clarin.weblicht.wlfxb.tc.xb;

import eu.clarin.weblicht.wlfxb.tc.api.Lemma;
import eu.clarin.weblicht.wlfxb.utils.CommonAttributes;
import java.util.Arrays;
import javax.xml.bind.annotation.*;

/**
 * @author Yana Panchenko
 *
 */
@XmlRootElement(name = LemmaStored.XML_NAME)
@XmlAccessorType(XmlAccessType.NONE)
public class LemmaStored implements Lemma {

    public static final String XML_NAME = "lemma";
    public static final String ID_PREFIX = "l_";
    @XmlValue
    protected String lemmaString;
    @XmlAttribute(name = CommonAttributes.ID)
    protected String lemmaId;
    @XmlAttribute(name = CommonAttributes.TOKEN_SEQUENCE_REFERENCE, required = true)
    protected String[] tokRefs;

    @Override
    public String getString() {
        return lemmaString;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lemmaId != null) {
            sb.append(lemmaId);
            sb.append(" -> ");
        }
        sb.append(lemmaString).append(" ").append(Arrays.toString(tokRefs));
        return sb.toString();
    }
}

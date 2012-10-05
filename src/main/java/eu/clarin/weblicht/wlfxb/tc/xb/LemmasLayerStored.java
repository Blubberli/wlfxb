/**
 *
 */
package eu.clarin.weblicht.wlfxb.tc.xb;

import eu.clarin.weblicht.wlfxb.tc.api.Lemma;
import eu.clarin.weblicht.wlfxb.tc.api.LemmasLayer;
import eu.clarin.weblicht.wlfxb.tc.api.Token;
import eu.clarin.weblicht.wlfxb.utils.WlfUtilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Yana Panchenko
 *
 */
@XmlRootElement(name = LemmasLayerStored.XML_NAME)
@XmlAccessorType(XmlAccessType.NONE)
public class LemmasLayerStored extends TextCorpusLayerStoredAbstract implements LemmasLayer {

    public static final String XML_NAME = "lemmas";
    @XmlElement(name = LemmaStored.XML_NAME)
    private List<LemmaStored> lemmas = new ArrayList<LemmaStored>();
    private TextCorpusLayersConnector connector;

    protected LemmasLayerStored() {
    }

    protected LemmasLayerStored(TextCorpusLayersConnector connector) {
        this.connector = connector;
    }

    protected void setLayersConnector(TextCorpusLayersConnector connector) {
        this.connector = connector;
        for (int i = 0; i < lemmas.size(); i++) {
            LemmaStored lemma = lemmas.get(i);
            for (String tokRef : lemma.tokRefs) {
                connector.token2ItsLemma.put(connector.tokenId2ItsToken.get(tokRef), lemma);
            }
            if (lemma.lemmaId == null) {
                lemma.lemmaId = LemmaStored.ID_PREFIX + "_" + i;
            }
            connector.lemmaId2ItsLemma.put(lemma.lemmaId, lemma);
        }
    }

    @Override
    public boolean isEmpty() {
        return lemmas.isEmpty();
    }

    @Override
    public int size() {
        return lemmas.size();
    }

    @Override
    public Lemma getLemma(int index) {
        Lemma lemma = lemmas.get(index);
        return lemma;
    }

    @Override
    public Lemma getLemma(Token token) {
        Lemma lemma = connector.token2ItsLemma.get(token);
        return lemma;
    }

    @Override
    public Token[] getTokens(Lemma lemma) {
        if (lemma instanceof LemmaStored) {
            LemmaStored lemmaStored = (LemmaStored) lemma;
            return WlfUtilities.tokenIdsToTokens(lemmaStored.tokRefs, connector.tokenId2ItsToken);
        } else {
            return null;
        }
    }

    @Override
    public Lemma addLemma(String lemmaString, Token lemmaToken) {
        List<Token> lemmaTokens = Arrays.asList(new Token[]{lemmaToken});
        return addLemma(lemmaString, lemmaTokens);
    }

    @Override
    public Lemma addLemma(String lemmaString, List<Token> lemmaTokens) {
        LemmaStored lemma = new LemmaStored();
        int lemmaCount = lemmas.size();
        lemma.lemmaId = LemmaStored.ID_PREFIX + lemmaCount;
        lemma.lemmaString = lemmaString;
        lemma.tokRefs = new String[lemmaTokens.size()];
        for (int i = 0; i < lemmaTokens.size(); i++) {
            Token lemmaToken = lemmaTokens.get(i);
            lemma.tokRefs[i] = lemmaToken.getID();
            connector.token2ItsLemma.put(lemmaToken, lemma);
        }
        lemmas.add(lemma);
        return lemma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(XML_NAME);
        sb.append(" : ");
        sb.append(lemmas.toString());
        return sb.toString();
    }
}

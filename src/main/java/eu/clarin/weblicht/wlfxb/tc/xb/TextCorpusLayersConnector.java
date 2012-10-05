package eu.clarin.weblicht.wlfxb.tc.xb;

import eu.clarin.weblicht.wlfxb.tc.api.*;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class TextCorpusLayersConnector {

    // maps for connecting elements of different layers that reference one another
    protected Map<String, Token> tokenId2ItsToken = new HashMap<String, Token>();
    protected List<TokenStored> tokens;
    protected Map<String, Lemma> lemmaId2ItsLemma = new HashMap<String, Lemma>();
    protected Map<Token, Lemma> token2ItsLemma = new HashMap<Token, Lemma>();
    protected Map<Token, PosTag> token2ItsPosTag = new HashMap<Token, PosTag>();
    protected Map<Token, Sentence> token2ItsSentence = new HashMap<Token, Sentence>();
    protected Map<Token, MorphologyAnalysis> token2ItsAnalysis = new HashMap<Token, MorphologyAnalysis>();
    //Map<Token,NamedEntity> token2ItsNE = new HashMap<Token,NamedEntity>();
    protected Map<Token, List<NamedEntity>> token2ItsNE = new HashMap<Token, List<NamedEntity>>();
    //Map<Token,Referent> token2ItsReferent = new HashMap<Token,Referent>();
    protected Map<Token, List<ReferencedEntity>> token2ItsReferent = new HashMap<Token, List<ReferencedEntity>>();
    protected Map<String, Reference> referenceId2ItsReference = new HashMap<String, Reference>();
    protected Map<Token, Relation> token2ItsRelation = new HashMap<Token, Relation>();
    protected Map<Token, WordSplit> token2ItsSplit = new HashMap<Token, WordSplit>();
    protected Map<Token, PhoneticsSegment> token2ItsPhseg = new HashMap<Token, PhoneticsSegment>();
    protected Map<Token, MatchedItem> token2ItsMatchedItem = new HashMap<Token, MatchedItem>();
    protected Map<Token, GeoPoint> token2ItsGeopoint = new HashMap<Token, GeoPoint>();
    protected Map<Lemma, Orthform> lemma2ItsSynonyms = new HashMap<Lemma, Orthform>();
    protected Map<Lemma, Orthform> lemma2ItsAntonyms = new HashMap<Lemma, Orthform>();
    protected Map<Lemma, Orthform> lemma2ItsHyponyms = new HashMap<Lemma, Orthform>();
    protected Map<Lemma, Orthform> lemma2ItsHyperonyms = new HashMap<Lemma, Orthform>();
    protected Map<String, EmptyTokenStored> emptyTokId2EmptyTok = new HashMap<String, EmptyTokenStored>();
    protected Map<Token, OrthCorrection> token2ItsCorrection = new HashMap<Token, OrthCorrection>();
    protected Map<TextSpanType, Map<Token, TextSpan>> token2ItsTextSpans = new EnumMap<TextSpanType, Map<Token, TextSpan>>(TextSpanType.class);
    protected Map<Token, DiscourseConnective> token2ItsDConnective = new HashMap<Token, DiscourseConnective>();
    protected Map<String, Constituent> constitId2ItsConstit = new HashMap<String, Constituent>();

    TextCorpusLayersConnector() {
        super();
        initSubmaps();
    }

    private void initSubmaps() {
        for (TextSpanType value : TextSpanType.values()) {
            token2ItsTextSpans.put(value, new HashMap<Token, TextSpan>());
        }
    }
}

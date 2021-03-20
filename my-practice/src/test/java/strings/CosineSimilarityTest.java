package strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CosineSimilarityTest {

    @Test
    void getSimilarity() {

        String src = "余弦相似度通常用于正空间，因此给出的值为-1到1之间";
        String dst = " 余弦相似性通过测量两个向量的夹角的余弦值来度量它们之间的相似性";

        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        System.out.println(cosineSimilarity.getSimilarity(src, dst));
    }
}
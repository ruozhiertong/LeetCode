import com.sun.corba.se.impl.copyobject.FallbackObjectCopierImpl;
import com.sun.tools.internal.xjc.model.CEnumConstant;
import sun.nio.ch.sctp.SctpNet;

/**
 * 1813. Sentence Similarity III
 * A sentence is a list of words that are separated by a single space with no leading or trailing spaces. For example, "Hello World", "HELLO", "hello world hello world" are all sentences. Words consist of only uppercase and lowercase English letters.
 *
 * Two sentences sentence1 and sentence2 are similar if it is possible to insert an arbitrary sentence (possibly empty) inside one of these sentences such that the two sentences become equal. For example, sentence1 = "Hello my name is Jane" and sentence2 = "Hello Jane" can be made equal by inserting "my name is" between "Hello" and "Jane" in sentence2.
 *
 * Given two sentences sentence1 and sentence2, return true if sentence1 and sentence2 are similar. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence1 = "My name is Haley", sentence2 = "My Haley"
 * Output: true
 * Explanation: sentence2 can be turned to sentence1 by inserting "name is" between "My" and "Haley".
 *
 *
 *
 * 思路： 是前缀，是后缀，或属于前后。
 *
 * @author rzet
 * @date 2023/1/16 23:03
 */
public class D16T1813 {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {

        int n1 = sentence1.length();
        int n2 = sentence2.length();

        String sent1, sent2;
        if (n1 > n2){
            sent1 = sentence1;
            sent2 = sentence2;
        }else {
            sent1 = sentence2;
            sent2 = sentence1;
        }
        n1 = sent1.length();
        n2 = sent2.length();

        if (sent1.startsWith(sent2))
            return true;

        if (sent1.endsWith(sent2))
            return true;

        int i, j;
        for (i = 0; i < n2; i++){
            if (sent1.charAt(i) !=  sent2.charAt(i))
                break;
        }

        int j2 = n1 -1;
        for (j = n2 -1; j >=0; j--, j2--){
            if (sent1.charAt(j2) != sent2.charAt(j))
                break;
        }

        if (i - 1 == j)
            return true;
        else
            return false;
    }
}

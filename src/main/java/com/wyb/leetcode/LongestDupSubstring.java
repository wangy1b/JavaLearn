package com.wyb.leetcode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 1044. 最长重复子串
 * 给出一个字符串 S，考虑其所有重复子串（S 的连续子串，出现两次或多次，可能会有重叠）。
 * <p>
 * 返回任何具有最长可能长度的重复子串。（如果 S 不含重复子串，那么答案为 ""。）
 * <p>
 * 示例 1：
 * 输入："banana"
 * 输出："ana"
 * <p>
 * 示例 2：
 * 输入："abcd"
 * 输出：""
 * <p>
 * 提示：
 * 2 <= S.length <= 10^5
 * S 由小写英文字母组成。
 * https://leetcode-cn.com/problems/longest-duplicate-substring/
 */
public class LongestDupSubstring {
    // 超出时间限制
    public String myLongestDupSubstring(String s) {
        String res = "";
        int len = s.length();
        if (len == 0) return res;
        HashSet<String> travel = new HashSet<>();

        // 改成倒序，倒过来找到当前重复的，就直接退出，必定时最长的
        for (int window = len - 1; window >= 1; window--) {
            System.out.println("window: " + window);
            travel.clear();
            // travel
            for (int i = 0; i < len - window + 1; i++) {
                String tmp = s.substring(i, i + window);
                if (travel.contains(tmp)) {
                    res = tmp;
                    return res;
                }
                travel.add(tmp);
            }
        }
        return res;
    }


    /*
    Rabin-Karp with polynomial rolling hash.
        Search a substring of given length
        that occurs at least 2 times.
        Return start position if the substring exits and -1 otherwise.
        */
    public int search(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[:L]
        long h = 0;
        for (int i = 0; i < L; ++i) h = (h * a + nums[i]) % modulus;

        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet();
        seen.add(h);
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= L; ++i) aL = (aL * a) % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
            h = (h + nums[start + L - 1]) % modulus;
            if (seen.contains(h)) return start;
            seen.add(h);
        }
        return -1;
    }

    /**
     * 方法一：二分查找 + Rabin-Karp 字符串编码
     * <p>
     * Go 语言的 strings 包（strings.go）中用到了 Rabin-Karp 算法。
     * Rabin-Karp 算法是基于这样的思路：即把字符串看作是字符集长度进制的数，由数值的比较结果得出字符串的比较结果。
     * abcd编码为：
     * pre = a * 26^3 + b * 26^2 + c * 26^1 +d
     * <p>
     * bcde编码为：
     * next = b * 26^3 + c * 26^2 + d * 26^1 +e
     * 利用pre计算为
     * next = (pre - a * 26^3) * 26 + e
     * <p>
     * 分析：
     * 我们可以把这个问题分解成两个子问题：
     * 1.从 1 到 N 中选取子串的长度 L；
     * 2.检查字符串中是否存在长度为 L 的重复子串。
     * <p>
     * 子任务一：二分查找
     * 解决子问题一的最简单的方法是，我们从 L = N - 1 开始，依次递减选取子串的长度，并进行判断。
     * 如果发现存在长度为 L 的重复子串，就表示 L 是最长的可能长度。
     * 但我们发现，如果字符串中存在长度为 L 的重复子串，那么一定存在长度为 L0 < L 的重复子串
     * （选取长度为 L 的重复子串的某个长度为 L0 的子串即可），因此我们可以使用二分查找的方法，找到最大的 L。
     * <p>
     * 子任务二：Rabin-Karp 字符串编码
     * <p>
     * 我们可以使用 Rabin-Karp 算法将字符串进行编码，这样只要有两个编码相同，就说明存在重复子串。对于选取的长度 L：
     * 使用长度为 L 的滑动窗口在长度为 N 的字符串上从左向右滑动；
     * 检查当前处于滑动窗口中的子串的编码是否已经出现过（用一个集合存储已经出现过的编码）；
     * * 若已经出现过，就说明找到了长度为 L 的重复子串；
     * * 若没有出现过，就把当前子串的编码加入到集合中。
     * <p>
     * 可以看出，Rabin-Karp 字符串编码的本质是对字符串进行哈希，将字符串之间的比较转化为编码之间的比较。
     * 接下来的问题是，在滑动窗口从左向右滑动时，如何快速计算出当前子串的编码呢？
     * 如果需要在 O(L)的时间内算出编码，这种方法就没有意义了，因为这个直接进行字符串比较需要的时间相同。
     * <p>
     * 为了能够快速计算出字符串编码，我们可以将字符串看成一个 26 进制的数（因为字符串中仅包含小写字母），
     * 它对应的 10 进制的值就是字符串的编码值。首先将字符转换为 26 进制中的 0-25，
     * 即通过 arr[i] = (int)S.charAt(i) - (int)'a'，可以将字符串 abcd 转换为 [0, 1, 2, 3]，它对应的 10 进制值为：
     * <p>
     * h0 = 0×26^3+1×26^2+2×26^1+3×26^0
     * <p>
     * <p>
     * 我们将这个表达式写得更通用一些，设 ci
     * ​
     * 为字符串中第 i 个字符对应的数字，a = 26 为字符串的进制，那么有：
     * <p>
     * h0 = c0*a^L−1 +c1*a^L−2+...+ci*a^L−1−i+...+cL−1*a^1+cL*a^0
     *
     *        L−1
     *    =   ∑ ci * a ^ (L−1−i)
     *        i=0
     *
     * 当我们向右移动滑动窗口时，例如从 abcd 变成 bcde，此时字符串对应的值从 [0, 1, 2, 3] 变成 [1, 2, 3, 4]，
     * 移除了最高位的 0，并且在最低位添加了 4，那么我们可以快速地计算出新的字符串的编码：
     * <p>
     * h1 = (h0 - 0 * 26^3) * 26 + 4 * 26^0
     * <p>
     * 更加通用的写法是：
     * <p>
     * h1 = (h0 a - c0 a^L) + c{L + 1}
     * <p>
     * 这样，我们只需要 O(L) 的时间复杂度计算出最左侧子串的编码，这个 O(L) 和滑动窗口的循环是独立的。
     * 在滑动窗口向右滑动时，计算新的子串的编码的时间复杂度仅为 O(1)。
     * <p>
     * 最后一个需要解决的问题是，在实际的编码计算中，a^L
     * 的值可能会非常大，在 C++ 和 Java 语言中，会导致整数的上溢出。
     * 一般的解决方法时，对编码值进行取模，将编码控制在一定的范围内，防止溢出，即h = h % modulus。
     * 根据 生日悖论，模数一般需要和被编码的信息数量的平方根的数量级相同，
     * 在本题中，相同长度的子串的数量不会超过 N 个，因此选取模数是 2^{32}
     * （无符号整型数的最大值）是足够的。在 Java 中可以用如下的代码实现取模：
     * <p>
     * h = (h * a - nums[start - 1] * aL % modulus + modulus) % modulus;
     * h = (h + nums[start + L - 1]) % modulus;
     * <p>
     * 而在 Python 中，整型数没有最大值，因此可以在运算的最后再取模：
     * h = (h * a - nums[start - 1] * aL + nums[start + L - 1]) % modulus
     * <p>
     * 在解决算法题时，我们只要判断两个编码是否相同，就表示它们对应的字符串是否相同。
     * 但在实际的应用场景中，会出现字符串不同但编码相同的情况，因此在实际场景中使用 Rabin-Karp 字符串编码时，
     * 推荐在编码相同时再对字符串进行比较，防止出现错误。
     * <p>
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-duplicate-substring/solution/zui-chang-zhong-fu-zi-chuan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    public String longestDupSubstringOfficial(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) nums[i] = (int) S.charAt(i) - (int) 'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 32);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left != right) {
            L = left + (right - left) / 2;
            if (search(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L;
        }

        int start = search(left - 1, a, modulus, n, nums);
        return start != -1 ? S.substring(start, start + left - 1) : "";
    }

    public static void main(String[] args) {
        LongestDupSubstring l = new LongestDupSubstring();
        // String s = "banana";
        // String s = "abcd";
        String s = "shabhlesyffuflsdxvvvoiqfjacpacgoucvrlshauspzdrmdfsvzinwdfmwrapbzuyrlvulpalqltqshaskpsoiispn" +
                "eszlcgzvygeltuctslqtzeyrkfeyohutbpufxigoeagvrfgkpefythszpzpxjwgklrdbypyarepdesko" +
                "toolwnmeibkqpiuvktejvbejgjptzfjpfbjgkorvgzipnjazzvpsjxjscekiqlcqeawsdydpsuqewszlpkgkrtlwxgo" +
                "zdqvyynlcxgnskjjmdhabqxbnnbflsscammppnlwyzycidzbhllvfvheujhnxrfujwmhwiamqplygauj" +
                "ruuptfdjmdqdndyzrmowhctnvxryxtvzzecmeqdfppsjczqtyxlvqwafjozrtnbvshvxshpetqijlzwgevdpwdkycmp" +
                "sehxtwzxcpzwyxmpawwrddvcbgbgyrldmbeignsotjhgajqhgrttwjesrzxhvtetifyxwiyydzxdqvokkv" +
                "fbrfihslgmvqrvvqfptdqhqnzujeiilfyxuehhvwamdkkvfllvdjsldijzkjvloojspdbnslxunkujnfbacgcuaiohd" +
                "ytbnqlqmhavajcldohdiirxfahbrgmqerkcrbqidstemvngasvxzdjjqkwixdlkkrewaszqnyiulnwaxfdb" +
                "yianmcaaoxiyrshxumtggkcrydngowfjijxqczvnvpkiijksvridusfeasawkndjpsxwxaoiydusqwkaqrjgkkzhkp" +
                "vlbuqbzvpewzodmxkzetnlttdypdxrqgcpmqcsgohyrsrlqctgxzlummuobadnpbxjndtofuihfjedkzakhvi" +
                "xkejjxffbktghzudqmarvmhmthjhqbxwnoexqrovxolfkxdizsdslenejkypyzteigpzjpzkdqfkqtsbbpnlmcjcve" +
                "artpmmzwtpumbwhcgihjkdjdwlfhfopibwjjsikyqawyvnbfbfaikycrawcbkdhnbwnhyxnddxxctwlywjc" +
                "isgqfsctzatdgqqauuvgclicdrpjcphysqdjaflpdbmvnhqggixxzcmpsysbwfkzwxzjictnng" +
                "ufpqhcxlbkodyrqlfomlkiefbmcfenugzqnyqqvgpxonmizkpjdlaqyyowjagzkzrzvcrupfyofeftyfvoqorzvxphh" +
                "dhydnqiyiczfcgzsecxzsoaobwrixcajabjnvtoerzwayjowahrmuixmmkbtchogfizmvbjnpespxn" +
                "gxjxntohzatlpkcmpphmewevpteharnszafbpbexrvnbedieojezdhnyooiivhnhakilvkobxepbksnqrtxxuqhalv" +
                "tjspyvporalbliiwjciamlhttaydhxoelimuorjnfvebjhcocbkrgbguwdncodskzzoqrzgavsbjcippe" +
                "tltqaxjhkqacwlgmsbxezqubyzeznnsoqegkykzlxohvitbmjcxllbrvgdijyovpjyeaojlyxqwnheyblzn" +
                "woyikhqiutotpfukyqkvatxotulvlqzfcvskdccuixthzqrwymzccosjmjqjigehcnfphjuuybaxxu" +
                "kconatzseljyirycbhucxmwwftulfwfmyqyprlnsmxzyfmgjctgeunuuexhbrbsaaingqxqrjvpuhbvcmyztmkgen" +
                "honajrkzfrqjinjrbmjyinhwvlcmmxvbgvjgfmaoliosmxbonvlzoiqvkxxtoposygcgkcotohcrauiv" +
                "xxvmrghuauadwojxjligrgstczirnvhqpzwgjbvqzlkxltqnqrfxieggnuriytavbnouwhuamdtlspednyc" +
                "kixkhxedjmotiuucewllthhducwgwmgzxsbkqzfnqfynwisvsctyqdoaiypjivtxkxgoyhwhccklbdjoqyk" +
                "aqzljejlizgbehekmkfe" +
                "tvgfstmypmfnyoundudqlorcogbzoznddfalthwpmiewkmvogmzirbprjftbtffjrkrfminnechitfyfaujgtugadq" +
                "brskulsjbaunonxolauvsifevpdyurvfocxtkizflcuvltzuhwyhlbxaphifhtgkfktfnnmocpenrlujs" +
                "uppbbuorvtubuiyszawzftijwhwgdyubjmmodzybiyunksuixnkariubegpdgctbayaynfskkuyhjvegsjw" +
                "sbppodvhpjdjlzhxixswdncapxyfjspxeqxdfkhockvrzoisikaymoiqzqbjyoscwegfomlnurwboe" +
                "sfiszetebjblaolnovgvfcpnbemwambkhwcgdbhvkoluhjfxlfrfaeedocdilaboesauplmttewlbojkocklhsbzr" +
                "tzeyhqtmgroupbzlymupmupsvlkzchclujuozzmngjvktzstsvocxrziuxelruwojzaleyrkjkdleavw" +
                "qxwgjdbtiywqtdtaamrlcjogxufhgvoqpqkgopbtyqchzhexqgecheahjzxapqjdylzjqhzlzssbjmoknc" +
                "xalgasexztnlzfisxxpeerywlrjdohprewwnlwdbtwmfnnxnoolfhqqxzcvoymdbvmaoliedpvwzyvgyrw" +
                "jguvoqnxrnaeqwvcfrqkwjmlvxovptultyfuxerelpfgctnpdxlu" +
                "qeruxkxqntosggfjqmrnlnkhhilznpycdrnemnktcsmzufpqgiraphzmgfhevzejhavsypohpttnnowfahpxfwmvx" +
                "gwfuomxemdkzdlzldesmowzmhwoydnsovwykxqyllbmcurlvtwcfwxvvkxfknwwcwfjkzjtonalgijdsu" +
                "lcfagehiptszrcltbbypopdbmdfkyofelmrdmdbceguyxnkheqqtbletpqmjugpckmjyuuvsbqhyzmposwc" +
                "gscnishluuhnwkyrkujefpgtsqrmcoortgitpdoagdncxlofkqozgngbtmlyyoyodcmcwetdtltupjrt" +
                "emrjswekkfjvfecmvagyptjjuwsqpjwlxxosqhpssdvjraaicjfwvesyqfbumjjbqytkinpldxopxjzmvpigmbero" +
                "byzyxwvwmlmbziduqhmbescgkvhgqtalmaxfsjlysmvrizgvrudstiwmaahtqehfbofvqwgqygvseykmgm" +
                "hgjbxcrtdjqvojvyhohallyewqelzhjeuqmmsqhkluvqsfmxzbqqokehfoqrlqmwpnwojfowqpqebnuggeu" +
                "vsszgfywceolvabyvbrwatuyherijsdqvpyyhdyradbammmchqkvdbxpbrxzrpfrsiiezvowrfqejibvoci" +
                "ujtcwbygvfwojgfn" +
                "vvwqlqqgipxhrogppzghtnweodaxuqxknnqnajlnsvheiycsvifvoljsncgnunsqcymnyoeeslrjflpprvtksimf" +
                "fvnuvakskdakvmlkpowfpfzdrcfctikhvvbagrvjlzjydnlmspzyynyjjfxnozpjjgjelipswsmfroitqph" +
                "zsuqgumlnkxksbzhrsvcnfwufofhurmhksvvfjzggbtgrezkrkqmhduyqgwuwxoxaiifemtwrbilftiuhcg" +
                "pjvqxldrnlzphdffncevlcyrxlpbwuswjfdegexeoooshdfqtqithpfocyowaqeedikssptyvkabhtaeotcwxccgguuotqvypug" +
                "pcbwzalxwqbjdcokoxjnqhggpbbfeyjiellsikiqqtxpvzmjsfleepjpbxpeicxfcwbpprzgcrjgjaxshewradetsq" +
                "svfmcxptmksecfpynqzpctqpogcgokzrkltsbmwxkmynasohpkzjupapngusnvdjfqezqhyikllgkelewww" +
                "hhbdjvxdagnnxscjkotbbmhzkqbjwuwidrnvmztikmqjcxmcpgkoudhydmdvberfuvjnh" +
                "lnfcsbpzmuquvrgogtfwefhqzkmxxgadtvjpxvurxprbsssihviypclwkjfaatzjxtvlzwaacqlwnqetgkldqaqghui" +
                "hrgxbbpmjfsvaigqrhiiskkfibaeilqptkdsqqfwxeixuxgkiboaqnuaeutjcydnxyxnmattjrrxmthwvyip" +
                "gazaxgrrjcvdnyxpktsldhluhicyqprxhljyfhawuvoonrwyklcdlmdvsgqrwqqomisksftsfyeifmupvyl" +
                "kjbagzyctuifbsrugqsbrkvskmundmczltpamhmgqespzgrkxebsvubrlmkwyqhjyljnkeqvdxtjxjvzlrubsiiahciwefwssw" +
                "gssxmvyvgjrobvubcbgjomqajmotbcgqjudneovfbjtjzwqtsovzshmxeqofssukkvcdwlsdtyplrlgwtehnwvhhegt" +
                "wkwnqqdiajpcaajsylesadaiflruewhrbrogbujbppunsqgytgnyuhnkejhccavaptbydtqhvyatftxcaalj" +
                "yhhkkadzdhhzawgndunwwgknnbtqaddpszqgummmnomfqmdxqtwjexsbadfdqhnyixjslsaisscocbabivz" +
                "okkgiinqqzsrtfpzjmxfkqmuzzlelqjtjidjarkwbwlcqrefokrlwdmuzyffdtajnqoimlz" +
                "zpcgpjjwlqkusefzbgznhexzojxnzxmmedobgvdabtdoiskozrdrjscxwivaekrkyyfynuktmgyziteavdxfctvkfkr" +
                "msdwpaywzbkeojeycwdkectydojttisizruilwokhepscqdnjygiakomkhyujaffxjyxqmvkemqihpcdygpr" +
                "deaxgjbkonfvgtzayfbmgwsskoyxjlknwwtehhhpjllhkcblyaxnbekoidbbyqvdqqsyfcemylmqskpxifc" +
                "nhmemkkitqtbfwhmyemkzightkjbhlquticivpeeclpamsqoztxvdtcqbsonxyecnhcadtghkjckhrcdfgg" +
                "nqlwurydzbeybqk" +
                "cfnnbwkciwaqdzgmcrbltvcuftxsqfpxnoombsbeoqxivgtkrjklxatgcorkdrvmngwlekeopgecefzxtprcoajoo" +
                "pxviijxilxfiwuajsbtcctfcqqgzhyjmonwdbyjlnneidyaqhhothzpzaxcthvbxpdcqofaeamxbqjwhunnqw" +
                "clhcqhagawjsxygorgbuqryzickyplbaivkabbrkibqzqacabbwmnpndaqvbknbqcjuywxjrdbznndomwbb" +
                "qfgulwczydrhrocebhygriiwxmwtjjyqqiqrjblxuamddlsiocdoysdaacuovljtpgocephnxuugdyggsnhc" +
                "qiqhulyhlxiwzvhrtbmvjnhacwunckqzhpcthqgsupptdwkfepr";
        // String res = l.longestDupSubstring(s);
        // String res = l.longestDupSubstringOfficial(s);
        // System.out.println(res);
        String res1 = l.myLongestDupSubstring2(s);
        System.out.println(res1);
    }

    // 字符串查找用得是rabin-karp，但是未用二分，所以时间复杂度还是O(m*n)
    // todo 二分
    private String myLongestDupSubstring2(String s){
        String res = "";
        int len = s.length();
        if (len == 0) return res;

        HashSet<Long> travel = new HashSet<>();
        // 进制
        int rk = 26;
        // 用来取模。防止数据溢出
        long maxVal = 1L<<31;

        // 改成倒序，倒过来找到当前重复的，就直接退出，必定时最长的
        for (int window = len - 1; window >= 1; window--) {
            // System.out.println("window: " + window);
            travel.clear();
            // 预计算
            // 每组最开始的值
            long sum = 0;
            // 每组a^window
            long wm = 1;
            for (int j = 0; j < window; j++) {
                int c = s.charAt(j) - (int)'a';
                sum = (sum * rk) % maxVal  + c;
                if (j > 0)
                    wm = (wm * rk) % maxVal;
            }
            travel.add(sum);

            // travel
            for (int i = 1; i < len - window + 1; i++) {
                // 当前窗口包含的字符串
                String tmp = s.substring(i, i + window);
                // 前一个窗口第一个值，要出窗口的
                int cpre = s.charAt(i-1) - (int)'a';
                // 当前窗口最后一个值，要进入的
                int clast = tmp.charAt(window-1) - (int)'a';
                sum = ((sum - (cpre * wm) %maxVal ) * rk)%maxVal + clast;
                if (travel.contains(sum)) {
                    res = tmp;
                    return res;
                } else
                    travel.add(sum);
            }

        }
        return res;
    }


}

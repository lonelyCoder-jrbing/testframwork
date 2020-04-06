package com.testframwork.algorithm;

import java.util.*;

public class Demo0 {
    //主要的计算方法。
    static Set<String> set = new HashSet<>();
    static int waterMark = 0;

    public static void DFS(List<String> candidate, String prefix) {
        if (prefix.length() == waterMark) {
            set.add(prefix);
        }
        for (int i = 0; i < candidate.size(); i++) {
            List<String> temp = new LinkedList<String>(candidate);
            String item = (String) temp.remove(i);  // 取出被删除的元素，这个元素当作一个组合用掉了
            DFS(temp, prefix + item);
        }
    }

    public static List<Integer> findSubstring(String s, String[] words) {
        set.clear();
        List<String> list = Arrays.asList(words);
        List<Integer> caculateList = new ArrayList<>();

        if (words.length != 0) {
            waterMark = words.length * words[0].length();
        }
        DFS(list, "");

        set.forEach(el -> {
            if (s.contains(el)) {
                int biglength = s.length();
                int littleLength = el.length();
                for (int i = 0; i < biglength - littleLength + 1; i++) {
                    int i1 = i + el.length();
                    if (s.substring(i, i + el.length()).equals(el)) {
                        caculateList.add(i);
                    }
                }

            }
        });
        return caculateList;
    }

    public static void main(String[] args) {
        long timeBegin = System.currentTimeMillis();
        set.clear();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "word"};
        String s3 = "barfoofoobarthefoobarman";
        String[] words3 = {"bar", "foo", "the"};
        String s2 = "barfoothefoobarman";
        String[] words2 = {"foo", "bar"};
        String s4 = "wordgoodgoodgoodbestword";
        String[] words4 = {"word", "good", "best", "good"};
        String s5 = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
        String s6 = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossm";
        String[] words5 = {"dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo", "cila", "tfty", "modg", "ztjg", "ybty", "heqg", "cpwo", "gdcj", "lnle", "sefg", "vimw", "bxcb"};
        String[] words6 = {"dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo", "cila"};
        List<Integer> substring = findSubstring(s2, words2);
        System.out.println(substring);
        System.out.println(System.currentTimeMillis() - timeBegin);
    }


    public int[] num = {1, 2, 3};
    public int[] a;
    public int[] vis;
    int ans;

    public void dfs(int x, int n) {

        if (x == n) {
            for (int i = 0; i < n; i++) {
                System.out.println(num[i] + " ");
            }
            System.out.println();
            this.ans++;


        } else {
            for (int i = 0; i < n; i++) {
                if (this.ans == 0) {
                    this.num[x] = this.a[i];
                    this.vis[i] = 1;
                    dfs(x + 1, n);
                    this.vis[i] = 0;

                }
            }
        }

        return;
    }

    public void Permutation(char chs[], int start) {

        if (start == chs.length - 1) {
            Arrays.toString(chs);
        }
        for (int i = start; i <= chs.length; i++) {
        }

    }


}

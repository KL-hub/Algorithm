package lixiang.string;

/**
 * @Description //TODO
 * @Author 李项
 * @Date 2020/4/16
 * @Version 1.0
 */
/**简单模式匹配，简称BF算法
 * 设主串S='aaaaaaaaccccccddddddd'   子串=‘cdddd’,返回子串在主串第pos之后的位置
 * 时间复杂度分析：最好的情况下O（m）,最坏的情况下O（m*n）
 */
public class 字符串匹配 {

    public static int getIndex(String  s,String  t ,int pos ){
        int i,j=0;
        i=pos;
        while (i<s.length() && j<t.length()){
            if(s.charAt(i) == t.charAt(j)){
                ++i;
                ++j;
            }else{
                i=i-j+1;
                j=0;
            }
        }
        if(i>t.length()) {return  i-t.length();}
        else {return 0;}
    }
    public static void main(String[] args) {
        System.out.println(getIndex("abcdefghiklmn","cd",0));
        System.out.println(getIndex("acabaabaabcacaabc","abaabcac",0));
    }

}
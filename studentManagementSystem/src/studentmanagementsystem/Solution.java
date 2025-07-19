package studentmanagementsystem;

class Solution {
    public String restoreString(String s, int[] indices) {
        String ans = "";
        for(int i = 0; i < indices.length; i++) {
            // ans[indices[i]] = s[i];
            ans += s.charAt(indices[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().restoreString("codeleet", new int[]{4,5,6,7,0,2,1,3}));
    }
}
package AorB;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    static RafilxMap<String,String> oHashHexBin = new RafilxMap<String,String>();

    static void getInitiatedHexBinValues() {
        oHashHexBin.put("0", "0000");
        oHashHexBin.put("1", "0001");
        oHashHexBin.put("2", "0010");
        oHashHexBin.put("3", "0011");
        oHashHexBin.put("4", "0100");
        oHashHexBin.put("5", "0101");
        oHashHexBin.put("6", "0110");
        oHashHexBin.put("7", "0111");
        oHashHexBin.put("8", "1000");
        oHashHexBin.put("9", "1001");
        oHashHexBin.put("A", "1010");
        oHashHexBin.put("B", "1011");
        oHashHexBin.put("C", "1100");
        oHashHexBin.put("D", "1101");
        oHashHexBin.put("E", "1110");
        oHashHexBin.put("F", "1111");
    }

    static private StringBuilder formatHexToBin(StringBuilder hexFormat) {
        StringBuilder binFormat = new StringBuilder();
        for (int i = 0; i < hexFormat.length(); i++) {
            binFormat.append(oHashHexBin.get(Character.toString(hexFormat.charAt(i))));
        }
        return binFormat;
    }

    static private void formatBinToHex(StringBuilder binFormat) {
        StringBuilder HexFormat = new StringBuilder();
        for (int i = 0; i < binFormat.length(); i+= 4) {
            HexFormat.append(oHashHexBin.getKey(binFormat.subSequence(i, i+4).toString()));
        }
        while (HexFormat.charAt(0) == '0' && HexFormat.length() > 1) {
            HexFormat.deleteCharAt(0);
            HexFormat.trimToSize();
        }

        System.out.println(HexFormat.toString());
    }

    static private Double getMaxLengthStrBuilder(StringBuilder oSBuilderBinA, StringBuilder oSBuilderBinB, StringBuilder oSBuilderBinC) {
        Double maxLength = (double) 0;

        if(oSBuilderBinA.length() > maxLength) {
            maxLength = (double) oSBuilderBinA.length();
        }
        if(oSBuilderBinB.length() > maxLength) {
            maxLength = (double) oSBuilderBinB.length();
        }
        if(oSBuilderBinC.length() > maxLength) {
            maxLength = (double) oSBuilderBinC.length();
        }

        return maxLength;
    }


    static private void formatSizesBuildersBin(StringBuilder sBuilder, int qnt) {
        for (int i = 0; i < qnt; i++) {
            sBuilder.insert(0, "0");
        }
    }

    static private int makeABorC(StringBuilder oSBuilderBinA, StringBuilder oSBuilderBinB, StringBuilder oSBuilderBinC, int k) {
        for (int i = 0; i < oSBuilderBinC.length(); i++) {
            if (oSBuilderBinC.charAt(i) == '0') {
                if (oSBuilderBinA.charAt(i) != '0') {
                    oSBuilderBinA.setCharAt(i, '0');
                    k--;
                }
                if (oSBuilderBinB.charAt(i) != '0') {
                    oSBuilderBinB.setCharAt(i, '0');
                    k--;
                }
            } else {
                if (oSBuilderBinA.charAt(i) == '0' && oSBuilderBinB.charAt(i) == '0') {
                    oSBuilderBinB.setCharAt(i, '1');
                    k--;
                }
            }
        }
        return k;
    }

    public static void aOrB(int k, String a, String b, String c) {
        getInitiatedHexBinValues();

        StringBuilder oSBuilderBinA = formatHexToBin(new StringBuilder(a));
        StringBuilder oSBuilderBinB = formatHexToBin(new StringBuilder(b));
        StringBuilder oSBuilderBinC = formatHexToBin(new StringBuilder(c));

        Double maxLength = getMaxLengthStrBuilder(oSBuilderBinA, oSBuilderBinB, oSBuilderBinC);

        formatSizesBuildersBin(oSBuilderBinA, maxLength.intValue() - oSBuilderBinA.length());
        formatSizesBuildersBin(oSBuilderBinB, maxLength.intValue() - oSBuilderBinB.length());
        formatSizesBuildersBin(oSBuilderBinC, maxLength.intValue() - oSBuilderBinC.length());

        k = makeABorC(oSBuilderBinA, oSBuilderBinB, oSBuilderBinC, k);

        boolean isPossible = true;
        isASmallerAsPossible:
        if(k < 0) {
            isPossible = false;
            System.out.println("-1");
        } else {
            for (int i = 0; i < oSBuilderBinA.length(); i++) {
                if (k == 0) {
                    break isASmallerAsPossible;
                }
                if (oSBuilderBinC.charAt(i) == '1' && oSBuilderBinA.charAt(i) == '1') {
                    if (k >= 2 && oSBuilderBinB.charAt(i) == '0') {
                        oSBuilderBinA.setCharAt(i, '0');
                        oSBuilderBinB.setCharAt(i, '1');
                        k -= 2;
                    } else if (oSBuilderBinB.charAt(i) == '1') {
                        oSBuilderBinA.setCharAt(i, '0');
                        k--;
                    }
                }
            }
        }
        if (isPossible) {
            formatBinToHex(oSBuilderBinA);
            formatBinToHex(oSBuilderBinB);
        }
    }

}

class RafilxMap<K,V> extends HashMap<K, V> {
    private Map<V,K> reverseMap = new HashMap<V,K>();

    @Override
    public V put(K key, V value) {
        reverseMap.put(value, key);
        return super.put(key, value);
    }

    public K getKey(V value){
        return reverseMap.get(value);
    }
}

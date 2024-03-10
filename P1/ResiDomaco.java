public class ResiDomaco {
    public static void main(String[] args) {
        System.out.println("pqrstu");
        for (int p=0; p<2; p++) {
            for (int q=0; q<2; q++) {
                for (int r=0; r<2; r++) {
                    for (int s=0; s<2; s++) {
                        for (int t=0; t<2; t++) {
                            for (int u=0; u<2; u++) {
                                System.out.println(imp(p, imp(q, imp(imp(r, s), imp(t, u)))));
                            }
                        }
                    }
                }
            }
        }
    }

    static int neg(int a) {
        if (a == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static int kon(int a, int b) {
        if (a == 1 && b == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    static int dis(int a, int b) {
        if (a == 1 || b == 1) {
            return 1;
        } else {
            return 0;
        }
    }

    static int imp(int a, int b) {
        if (a == 1 && b == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    static int ekv(int a, int b) {
        if (a == b) {
            return 1;
        } else {
            return 0;
        }
    }
}

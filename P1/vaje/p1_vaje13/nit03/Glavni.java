import java.util.*;


public class Glavni {
    public static void urediPoLetuInNaslovu(List<Clanek> clanki) {
        clanki.sort(Clanek.poLetuInNaslovu());
    }

    public static Set<Avtor> vsiAvtorji(Collection<Clanek> clanki) {
        Set<Avtor> avtorji = new HashSet<>();

        for (Clanek clanek: clanki) {
            for (Avtor avtor: clanek.vrniAvtorje()) {
                avtorji.add(avtor);
            }
        }

        /*
        for (Clanek clanek: clanki) {
            avtorji.addAll(clanek.vrniAvtorje());
        }
        */

        return avtorji;
    }

    public static Map<Avtor, List<Clanek>> clankiPoAvtorjih(Collection<Clanek> clanki) {
        Map<Avtor, List<Clanek>> clankiPoAvtorjih = new HashMap<>();
        Set<Avtor> avtorji = vsiAvtorji(clanki);

        for (Avtor avtor: avtorji) {
            for (Clanek clanek: clanki) {
                if (clanek.vrniAvtorje().contains(avtor)) {
                    if (clankiPoAvtorjih.get(avtor) == null) {
                        clankiPoAvtorjih.put(avtor, new ArrayList<>());
                    }

                    clankiPoAvtorjih.get(avtor).add(clanek);
                    clankiPoAvtorjih.get(avtor).sort(Clanek.poLetuInNaslovu());
                }
            }
        }

        return clankiPoAvtorjih;
    }

    public static Avtor najplodnejsiAvtor(Collection<Clanek> clanki) {
        Map<Avtor, List<Clanek>> mapa = clankiPoAvtorjih(clanki);
        
        return Collections.max(vsiAvtorji(clanki),
                (avtor1, avtor2) -> mapa.get(avtor1).size() - mapa.get(avtor2).size()
        );
    }
}

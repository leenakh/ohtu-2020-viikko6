package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        // seuraavassa osoitteessa 27.11.2019 päivitetyt tilastot
        String url = "https://nhl27112019.herokuapp.com/players.txt";
        // ajan tasalla olevat tilastot osoitteessa
        // "https://nhlstatisticsforohtu.herokuapp.com/players.txt"

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        
        Matcher a = new All();
        Matcher n = new Not(new PlaysIn("WPG"), new HasAtLeast(1, "goals"));
        Matcher nyr = new And(new Not(new HasAtLeast(1, "goals")), new PlaysIn("NYR"));
        Matcher f = new And(new PlaysIn("NYR"), new HasFewerThan(1, "assists"));
        Matcher o = new And(new HasAtLeast(20, "points"), new Or(new PlaysIn("NYR"), new PlaysIn("NYI"), new PlaysIn("NJD")));
        Matcher o2 = new Or(new PlaysIn("NYR"), new HasAtLeast(5, "goals"));
        Matcher o3 = new Or(new And(new PlaysIn("NYR"), new HasAtLeast(5, "goals")), new And(new PlaysIn("EDM"), new HasAtLeast(20, "points")));
        
        QueryBuilder query = new QueryBuilder();
        Matcher newAll = query.build();
        Matcher newNYR = query.playsIn("NYR").build();
        Matcher newCombo = query.playsIn("NYR").hasAtLeast(5, "goals").hasFewerThan(10, "goals").build();
        Matcher or = query.oneOf(query.playsIn("PHI")
                .hasAtLeast(10, "assists")
                .hasFewerThan(8, "goals").build(),
                
                query.playsIn("EDM")
                .hasAtLeast(20, "points").build()
        ).build();
        
        Matcher or2 = query.oneOf(query.playsIn("NYR").build(), query.hasAtLeast(20, "points").build()).build();
                        
        for (Player player : stats.matches(or2)) {
            System.out.println(player);
        }
    }
}

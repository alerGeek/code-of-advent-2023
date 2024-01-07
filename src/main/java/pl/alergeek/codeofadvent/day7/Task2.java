package pl.alergeek.codeofadvent.day7;

import pl.alergeek.codeofadvent.day7.model.HandBid;
import pl.alergeek.codeofadvent.day7.services.HandBidParser;
import pl.alergeek.model.Task;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.ToLongFunction;

public class Task2 implements Task {

    @Override
    public String solve(List<String> input) {
        AtomicLong rankAtomic = new AtomicLong(1);
        List<HandBid> list = new HandBidParser().parseWithJoker(input);
        long sum = list.stream()
                .mapToLong(calculateRank(rankAtomic))
                .sum();

        assert sum != 251776722L;
        assert sum != 251193538L;
        assert sum != 251013393L;

        return String.valueOf(sum);
    }

    private ToLongFunction<HandBid> calculateRank(AtomicLong rankAtomic) {
        return handBid -> {
            long rank = rankAtomic.get();
            rankAtomic.incrementAndGet();
            return rank * handBid.bid();
        };
    }
}

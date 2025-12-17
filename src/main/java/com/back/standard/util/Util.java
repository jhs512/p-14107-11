package com.back.standard.util;

import java.time.Duration;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class Util {
    public static class thread {
        private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(100); // 10,000초 타임아웃
        private static final Duration DEFAULT_INTERVAL = Duration.ofMillis(500);    // 폴링 간격(기본 50ms)

        public static void waitUntil(BooleanSupplier condition) {
            waitUntil(condition, DEFAULT_TIMEOUT, DEFAULT_INTERVAL, "조건이 시간 내에 충족되지 않았습니다.");
        }

        public static void waitUntil(BooleanSupplier condition, Duration timeout) {
            // 폴링 간격은 기본값 사용
            waitUntil(condition, timeout, DEFAULT_INTERVAL, "조건이 시간 내에 충족되지 않았습니다.");
        }

        public static void waitUntil(BooleanSupplier condition, Duration timeout, Duration interval) {
            waitUntil(condition, timeout, interval, "조건이 시간 내에 충족되지 않았습니다.");
        }

        public static void waitUntil(BooleanSupplier condition,
                                     Duration timeout,
                                     Duration interval,
                                     String timeoutMessage) {

            Objects.requireNonNull(condition, "condition");
            Objects.requireNonNull(timeout, "timeout");
            Objects.requireNonNull(interval, "interval");

            if (timeout.isNegative()) throw new IllegalArgumentException("timeout은 0 이상이어야 합니다.");
            if (interval.isNegative() || interval.isZero()) throw new IllegalArgumentException("interval은 0보다 커야 합니다.");

            final long deadlineNanos = System.nanoTime() + timeout.toNanos();

            while (true) {
                // 조건이 충족되면 즉시 종료
                if (condition.getAsBoolean()) return;

                // 타임아웃 검사
                if (System.nanoTime() >= deadlineNanos) {
                    throw new IllegalStateException(timeoutMessage + " (timeout=" + timeout + ")");
                }

                // 잠깐 쉬었다가 다시 체크(폴링)
                try {
                    Thread.sleep(interval.toMillis());
                } catch (InterruptedException e) {
                    // 인터럽트 상태 복구 후 예외로 중단(원하면 return으로 바꿔도 됨)
                    Thread.currentThread().interrupt();
                    throw new IllegalStateException("대기 중 인터럽트로 중단되었습니다.", e);
                }
            }
        }
    }
}

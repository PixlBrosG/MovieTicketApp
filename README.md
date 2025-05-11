# 🎟️ Movie Ticket Booking – Thread Safety Demo

This project demonstrates the effects of thread synchronization (or lack thereof) in a simple multithreaded Java application. It simulates a movie ticket booking system, where multiple threads (clients) try to book tickets concurrently through a shared server.

The purpose is to observe how different combinations of `synchronized` and `volatile` affect the correctness of shared state (`availableTickets`).

---

## 🚀 How to Run

This project uses **Gradle**. You can run the app with:

```
./gradlew run
```

Or run it manually:

```
./gradlew build
java -jar build/libs/MovieTicketApp-1.0.0.jar
```

---

## 🧪 Test Variants

The system tests **four different implementations** of the `MovieTicketServer`, each varying in thread-safety features:

| Server Variant                     | `synchronized` | `volatile` | Description                    |
|------------------------------------|----------------|------------|--------------------------------|
| `MovieTicketServerSyncOnly`        | ✅              | ❌          | Correctly synchronized booking |
| `MovieTicketServerVolatileOnly`    | ❌              | ✅          | Visibility without atomicity   |
| `MovieTicketServerSyncAndVolatile` | ✅              | ✅          | Redundant but still correct    |
| `MovieTicketServerUnsynced`        | ❌              | ❌          | Fully unsafe and inconsistent  |

---

## 📋 Report: Output Comparison

### ✅ `MovieTicketServerSyncOnly`

```
Xiangming is trying to book 3 tickets. Available: 10
Xiangming successfully booked 3 tickets. Remaining: 7
Andreas is trying to book 4 tickets. Available: 7
Andreas successfully booked 4 tickets. Remaining: 3
Sam is trying to book 3 tickets. Available: 3
Sam successfully booked 3 tickets. Remaining: 0
Ilaria is trying to book 2 tickets. Available: 0
Ilaria failed to book 2 tickets. Not enough available.
Final available tickets: 0
```

### ⚠️ `MovieTicketServerVolatileOnly`

```
Xiangming is trying to book 3 tickets. Available: 10
Ilaria is trying to book 2 tickets. Available: 10
Sam is trying to book 3 tickets. Available: 10
Andreas is trying to book 4 tickets. Available: 10
Andreas successfully booked 4 tickets. Remaining: 1
Sam successfully booked 3 tickets. Remaining: 1
Ilaria successfully booked 2 tickets. Remaining: 1
Xiangming successfully booked 3 tickets. Remaining: 1
Final available tickets: 1
```

### ✅ `MovieTicketServerSyncAndVolatile`

```
Xiangming is trying to book 3 tickets. Available: 10
Xiangming successfully booked 3 tickets. Remaining: 7
Andreas is trying to book 4 tickets. Available: 7
Andreas successfully booked 4 tickets. Remaining: 3
Sam is trying to book 3 tickets. Available: 3
Sam successfully booked 3 tickets. Remaining: 0
Ilaria is trying to book 2 tickets. Available: 0
Ilaria failed to book 2 tickets. Not enough available.
Final available tickets: 0
```

### ❌ `MovieTicketServerUnsynced`

```
Xiangming is trying to book 3 tickets. Available: 10
Ilaria is trying to book 2 tickets. Available: 10
Sam is trying to book 3 tickets. Available: 10
Andreas is trying to book 4 tickets. Available: 10
Andreas successfully booked 4 tickets. Remaining: 4
Ilaria successfully booked 2 tickets. Remaining: 4
Xiangming successfully booked 3 tickets. Remaining: 4
Sam successfully booked 3 tickets. Remaining: 4
Final available tickets: 4
```

---

## 📊 Summary

- **`synchronized`** is essential to prevent race conditions during multi-step operations like checking and updating shared state.
- **`volatile`** alone is not enough. It ensures visibility, but not atomicity.
- Combining both (`synchronized + volatile`) is redundant here, as `synchronized` already guarantees memory visibility.
- **No synchronization at all** leads to serious inconsistencies: tickets may be oversold or under-accounted, with final values that defy logic.

---

## 💡 Conclusion

If you care about data consistency in concurrent Java applications — **always use proper synchronization** when modifying shared mutable state. `volatile` is useful, but not a substitute for atomicity.

---

## ✨ Optional Improvements

- Add color-coded output with ANSI escape codes
- Log to files for large-scale test runs
- Use randomized thread order for more chaotic results

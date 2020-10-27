# EdgeDB-JAVA
This is an unofficial EdgeDB driver for JAVA ecosystem.

# ⚠️ WIP ⚠️
This project is far from production ready. Contributions welcome! 😊

## Basic Recipes
```java

package edgedb.client;

import edgedb.exceptions.FailedToConnectEdgeDBServer;
import edgedb.exceptions.FailedToDecodeServerResponseException;

import java.io.IOException;

public class ReadMeExample {

    public static void main(String[] args) {
        String dsn = "edgedb://edgedb@localhost:5656/tutorial";
        EdgeDBClient db = new EdgeDBClient().withDSN(dsn);

        try {
            db.connect();
            String jsonResult = db.execute("SELECT Movie { id, title}");
            System.out.Println(result);
            db.terminate();
        } catch (FailedToDecodeServerResponseException |FailedToConnectEdgeDBServer| IOException e) {
            e.printStackTrace();
        } 
    }
}

```

## License
edgedb-java is developed and distributed under the Apache 2.0 license.
    
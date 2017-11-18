package state.sample.martinfowler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registrar {
    private static Map<String, List<DomainObject>> domainObjects;

    public Registrar(Map<String, List<DomainObject>> domainObjects) {
        this.domainObjects = domainObjects == null ? new HashMap<String, List<DomainObject>>() : domainObjects;
    }

    public static void add(String movies, DomainObject movie) {
        List<DomainObject> specificDomainObjects = domainObjects.get(movies);
        specificDomainObjects.add(movie);
    }

    public static DomainObject get(String movies, String name) {
        List<DomainObject> specificDomainObjects = domainObjects.get(movies);
        for (DomainObject domainObject : specificDomainObjects) {
            if (domainObject.name().equals(name)) {
                return domainObject;
            }
        }
        return new DomainObject("empty domain object");
    }
}

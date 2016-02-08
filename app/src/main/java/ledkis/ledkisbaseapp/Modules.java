package ledkis.ledkisbaseapp;

final class Modules {
    static Object[] list(LedkisBaseApplication app) {
        return new Object[]{
                new LedkisBaseAppModule(app)
        };
    }

    private Modules() {
        // No instances.
    }
}

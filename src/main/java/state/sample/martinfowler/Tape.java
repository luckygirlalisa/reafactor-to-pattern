package state.sample.martinfowler;

class Tape extends DomainObject
{
    public Movie movie() {
        return _movie;
    }
    public Tape(String serialNumber, Movie movie) {
        _serialNumber = serialNumber;
        _movie = movie;
    }
    private String _serialNumber;
    private Movie _movie;
}

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Application
 */
public class Application {

    public static void main(String[] args) {
        
        List<Album> albums = List.of(SampleData.aLoveSupreme, SampleData.sampleShortAlbum, SampleData.manyTrackAlbum);

        Application test = new Application();

        Set<String> longTracks = test.findLongTracks(albums);
        Set<String> longTracksStream = test.findLongTracksRef(albums);

        System.out.println("List of long tracks (Original method): ");
        for (String trackName : longTracks) {
            System.out.println(" - " + trackName);
        }

        System.out.println("List of long tracks (Refacted method): ");
        for (String trackName : longTracksStream) {
            System.out.println(" - " + trackName);
        }
    }

    // Origianl method
    public Set<String> findLongTracks(List<Album> albums) {
        Set<String> trackNames = new HashSet<>(); for(Album album : albums) {
            for (Track track : album.getTrackList()) { 
                if (track.getLength() > 60) {
                 String name = track.getName();
                 trackNames.add(name);
                }
            } 
        }
        return trackNames; 
    }

    // Refactored method
    public Set<String> findLongTracksRef(List<Album> albums) {
        return albums.stream() 
                .flatMap(album -> album.getTrackList().stream())
                .filter(track -> track.getLength() > 60)
                .map(Track::getName)
                .collect(Collectors.toSet());
    }
}
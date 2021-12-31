import java.util.ArrayList;
import java.util.LinkedList;

public class Album {
    private String name;
    private String artist;
    private ArrayList<Song> songs;

    public Album(String name, String artist){
        this.name=name;
        this.artist=artist;
        this.songs = new ArrayList<>();
    }

    public boolean addSong(String songTitle, double songDuration){
        if(checkSong(songTitle)==null){
            this.songs.add(new Song(songTitle, songDuration));
            return true;
        }
        return false;
    }

    private Song checkSong(String songTitle) {
        for (Song song : this.songs) {
            if(song.getSongTitle().equalsIgnoreCase(songTitle)){
                return song;
            }
        }

        return null;
    }

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }

    public boolean addToPlaylist(int trackNumber, LinkedList<Song> playList){
        int index = trackNumber-1;
        if(index>=0&&index<=this.songs.size()){
            playList.add(this.songs.get(index));
            return true;
        }

        System.out.println("track "+ trackNumber +" not available in album");
        return false;
    }

    public boolean addToPlaylist(String title, LinkedList<Song> playList){
        Song checkedSong = checkSong(title);
        if(checkedSong!=null){
            playList.add(checkedSong);
            return true;
        }
        System.out.println(title + " not present in playlsit");
        return false;
    }
}

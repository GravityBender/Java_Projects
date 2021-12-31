import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();
    public static void main(String[] args) {
        Album album = new Album("Pop", "Various Artists");
        album.addSong("Dusk Till Dawn", 3.59);
        album.addSong("Coutesy Call", 3.56);
        album.addSong("StarBoy", 3.50);
        albums.add(album);

        album = new Album("EDM", "Various Artists");
        album.addSong("Shadows", 2.39);
        album.addSong("Why Do I?", 3.43);
        album.addSong("Battlecry", 4.00);
        albums.add(album);

        LinkedList<Song> playList = new LinkedList<Song>();
        albums.get(0).addToPlaylist("Dusk Till Dawn", playList);
        albums.get(0).addToPlaylist(3, playList);
        albums.get(1).addToPlaylist("Why Do I?", playList);
        albums.get(1).addToPlaylist(1, playList);

        play(playList);
    }

    private static void play(LinkedList<Song> playList){
        
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        ListIterator<Song> listIterator = playList.listIterator();

        if(playList.size() == 0) {
            System.out.println("No songs in playlist");
            return;
        } else {
            System.out.println("Now playing " + listIterator.next().toString());
            printMenu();
        }

        while(!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();

            switch(action) {
                case 0:
                    System.out.println("Playlist complete.");
                    quit = true;
                    break;
                case 1:
                    if(!forward) {
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if(listIterator.hasNext()) {
                        System.out.println("Now playing " + listIterator.next().toString());
                    } else {
                        System.out.println("We have reached the end of the playlist");
                        forward = false;
                    }
                    break;

                case 2:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if(listIterator.hasPrevious()) {
                        System.out.println("Now playing " + listIterator.previous().toString());
                    } else {
                        System.out.println("We are at the start of the playlist");
                        forward = true;
                    }
                    break;
                case 3:
                    if(forward) {
                        if(listIterator.hasPrevious()) {
                            System.out.println("Now replaying " + listIterator.previous().toString());
                            forward = false;
                        } else {
                            System.out.println("We are at the start of the list");
                        }
                    } else {
                        if(listIterator.hasNext()) {
                            System.out.println("Now replaying " + listIterator.next().toString());
                            forward = true;
                        } else {
                            System.out.println("We have reached the end of the list");
                        }
                    }
                    break;
                case 4:
                    printList(playList);
                    break;
                case 5:
                    printMenu();
                    break;

                case 6:
                    if(playList.size() >0) {
                        listIterator.remove();
                        if(listIterator.hasNext()) {
                            System.out.println("Now playing " + listIterator.next());
                        } else if(listIterator.hasPrevious()) {
                            System.out.println("Now playing " + listIterator.previous());
                        }
                    }
                    break;

            }
        }
    }

    private static void printMenu() {

        System.out.println("Available actions:\npress");
        System.out.println("0 : to quit\n" +
                "1 : to play next song\n" +
                "2 : to play previous song\n" +
                "3 : to replay the current song\n" +
                "4 : list songs in the playlist\n" +
                "5 : print available actions.\n" +
                "6 : delete current song from playlist");
    }

    private static void printList(LinkedList<Song> playList) {
        
        Iterator<Song> iterator = playList.iterator();
        System.out.println("================================");
        
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("================================");
    }
}

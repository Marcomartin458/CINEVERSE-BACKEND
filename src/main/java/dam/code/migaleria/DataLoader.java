package dam.code.migaleria;

import dam.code.migaleria.models.Pelicula;
import dam.code.migaleria.models.Pelicula.Genero;
import dam.code.migaleria.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public void run(String... args) {
        if (peliculaRepository.count() > 0) return;

        List<Pelicula> peliculas = List.of(
                new Pelicula("Cómo entrenar a tu dragón",
                        "Chris Sanders, Dean DeBlois", Genero.AVENTURA,
                        "Hipo, un joven vikingo, se hace amigo de un dragón herido y descubre que no son las bestias que su tribu cree.",
                        "https://www.alpedrete.es/wp-content/uploads/2016/07/C%C3%B3mo-entrenar-a-tu-Drag%C3%B3n-430x323.jpg"),

                new Pelicula("Ratatouille",
                        "Brad Bird", Genero.COMEDIA,
                        "Una rata con un talento culinario excepcional forma una extraña alianza con un joven chef en París.",
                        "https://m.media-amazon.com/images/M/MV5BMTMzODU0NTkxMF5BMl5BanBnXkFtZTcwMjQ4MzMzMw@@._V1_.jpg"),

                new Pelicula("Los Increíbles",
                        "Brad Bird", Genero.ACCION,
                        "Una familia de superhéroes oculta sus poderes, pero debe volver a la acción para salvar el mundo.",
                        "https://m.media-amazon.com/images/M/MV5BMmJlODRiYzktNTk5OC00YmY3LWEzODQtZmU3MzA5Mjg3YTQ1XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"),

                new Pelicula("Toy Story",
                        "John Lasseter", Genero.AVENTURA,
                        "Los juguetes de Andy cobran vida y se enfrentan al miedo de ser reemplazados cuando llega Buzz Lightyear.",
                        "https://cdn.cultura.com/cdn-cgi/image/width=830/media/pim/TITELIVE/63_9782014634532_1_75.jpg"),

                new Pelicula("Shrek",
                        "Andrew Adamson, Vicky Jenson", Genero.COMEDIA,
                        "Un ogro cascarrabias ve su soledad interrumpida por un grupo de criaturas de cuento que invaden su hogar.",
                        "https://es.web.img3.acsta.net/r_1280_720/medias/nmedia/18/97/04/22/20504502.jpg"),

                new Pelicula("Del revés (Inside Out)",
                        "Pete Docter", Genero.DRAMA,
                        "Las emociones dentro de la mente de una niña aprenden a trabajar juntas cuando su familia se muda de ciudad.",
                        "https://images.cdn2.buscalibre.com/fit-in/360x360/81/19/8119e3cbd8375b1db2a544312ec2b869.jpg"),

                new Pelicula("Kung Fu Panda",
                        "Mark Osborne, John Stevenson", Genero.ACCION,
                        "Un torpe panda es elegido como el Guerrero Dragón para defender el Valle de la Paz de una antigua amenaza.",
                        "https://cdng.europosters.eu/pod_public/1300/261561.jpg"),

                new Pelicula("Big Hero 6",
                        "Don Hall, Chris Williams", Genero.ACCION,
                        "Un joven prodigio de la robótica y su inflable robot de compañía se unen a un equipo de héroes para salvar su ciudad.",
                        "https://static.posters.cz/image/750/23280.jpg"),

                new Pelicula("Gru, mi villano favorito",
                        "Pierre Coffin, Chris Renaud", Genero.COMEDIA,
                        "Un villano planea robar la Luna, pero su vida cambia al adoptar a tres huérfanas que lo tratan como un padre.",
                        "https://pics.filmaffinity.com/Gru_mi_villano_favorito-274176973-large.jpg"),

                new Pelicula("Up",
                        "Pete Docter", Genero.DRAMA,
                        "Un viudo anciano ata miles de globos a su casa para volar a Sudamérica, sin esperar llevar a un joven polizón.",
                        "https://es.web.img2.acsta.net/r_1280_720/medias/nmedia/18/68/08/67/20473292.jpg"),

                new Pelicula("Buscando a Nemo",
                        "Andrew Stanton", Genero.DRAMA,
                        "Un pez payaso sobreprotector recorre el océano para rescatar a su hijo, capturado por un buceador.",
                        "https://www.polifemo.com/static/img/portadas/_visd_0000JPG01P3B.jpg"),

                new Pelicula("Frozen",
                        "Chris Buck, Jennifer Lee", Genero.FANTASIA,
                        "Una reina con poderes de hielo huye a las montañas tras desatar un invierno eterno, y su hermana va a buscarla.",
                        "https://cdn.europosters.eu/image/1300/15923.jpg"),

                new Pelicula("Brave (Indomable)",
                        "Mark Andrews, Brenda Chapman", Genero.FANTASIA,
                        "Una princesa escocesa recurre a una bruja para cambiar su destino, desatando un antiguo hechizo.",
                        "https://danieldokter.wordpress.com/wp-content/uploads/2012/06/brave-1.jpg"),

                new Pelicula("La bella y la bestia",
                        "Gary Trousdale, Kirk Wise", Genero.FANTASIA,
                        "Una joven se ofrece prisionera a una bestia en su castillo encantado, sin saber que es un príncipe bajo un hechizo.",
                        "https://vueltalainfancia.wordpress.com/wp-content/uploads/2011/12/caratula-bella-y-bestia.jpg"),

                new Pelicula("Pesadilla antes de Navidad",
                        "Henry Selick", Genero.TERROR,
                        "Jack Skellington, rey de Halloween, descubre la Navidad e intenta apoderarse de ella con resultados espeluznantes.",
                        "https://cartelesmix.es/cartelesdecine/wp-content/uploads/2018/01/pesadillaantesnavidad93001.jpg"),

                new Pelicula("Coraline",
                        "Henry Selick", Genero.TERROR,
                        "Una niña descubre una puerta secreta a un mundo paralelo donde todo parece mejor, hasta que resulta ser una trampa.",
                        "https://www.critikat.com/wp-content/uploads/2009/06/artoff3182.jpg"),

                new Pelicula("Monstruos, S.A.",
                        "Pete Docter", Genero.COMEDIA,
                        "Dos monstruos asustadores descubren que la risa es más poderosa que los gritos cuando una niña humana entra en su mundo.",
                        "https://pics.filmaffinity.com/monsters_inc-336355219-mmed.jpg"),

                new Pelicula("Wall-E",
                        "Andrew Stanton", Genero.CIENCIA_FICCION,
                        "Un robot solitario en una Tierra abandonada se enamora de una moderna robot exploradora y la sigue al espacio.",
                        "https://m.media-amazon.com/images/M/MV5BZDkxZWE0MmUtYjY0NC00MmI3LWI0MTUtMGVmMjZhZDE2N2MzXkEyXkFqcGc@._V1_QL75_UX190_CR0,2,190,281_.jpg"),

                new Pelicula("Spider-Man: Un nuevo universo",
                        "Bob Persichetti, Peter Ramsey", Genero.CIENCIA_FICCION,
                        "Miles Morales se convierte en Spider-Man y une fuerzas con versiones alternativas del héroe para salvar el multiverso.",
                        "https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_QL75_UX190_CR0,0,190,281_.jpg"),

                new Pelicula("El gigante de hierro",
                        "Brad Bird", Genero.CIENCIA_FICCION,
                        "Un niño descubre a un robot gigante caído del espacio y debe protegerlo de los militares que quieren destruirlo.",
                        "https://m.media-amazon.com/images/M/MV5BYzVmNmZmM2EtMWJiZi00ZmFiLTljMjEtYjk3OWYyZWZhNmI0XkEyXkFqcGc@._V1_.jpg"),

                new Pelicula("El libro de la selva",
                        "Wolfgang Reitherman", Genero.AVENTURA,
                        "Mowgli, un niño criado por lobos, emprende un viaje por la jungla acompañado de Baloo y Bagheera.",
                        "https://oyster.ignimgs.com/wordpress/stg.ign.com/2015/08/JBPoster.jpg")
        );

        peliculaRepository.saveAll(peliculas);
        System.out.println("[DataLoader] " + peliculas.size() + " películas cargadas en la BD.");
    }
}
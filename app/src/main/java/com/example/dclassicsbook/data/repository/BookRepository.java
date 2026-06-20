package com.example.dclassicsbook.data.repository;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookRepository {

    public static List<Book> getBooks() {
        List<Book> list = new ArrayList<>();

        list.add(new Book(1, "Pride and Prejudice", "Jane Austen", "1813",
                Arrays.asList("Romance", "Drama", "Social", "Classic"),
                "Menggambarkan kehidupan sosial masyarakat Inggris abad ke-19 melalui kisah Elizabeth Bennet, seorang wanita cerdas yang menghadapi dinamika cinta, kelas sosial, dan prasangka. Interaksinya dengan Mr. Darcy memperlihatkan bagaimana kesalahpahaman dan penilaian awal dapat menghalangi hubungan yang tulus. Novel ini tetap relevan hingga kini sebagai refleksi mendalam tentang karakter, moralitas, dan relasi antarmanusia.",
                R.drawable.pride_and_prejudice));

        list.add(new Book(2, "Nineteen eighty-four", "George Orwell", "1984",
                Arrays.asList("Dystopian", "Political", "Sci-Fi", "Thriller"),
                "Sebagai salah satu karya distopia paling berpengaruh, novel ini menghadirkan gambaran dunia totaliter yang mengontrol pikiran, bahasa, dan realitas melalui kekuasaan absolut. Tokoh Winston Smith menjadi simbol perlawanan individu terhadap sistem yang menindas kebebasan dan kebenaran. Hingga saat ini, karya ini diakui sebagai peringatan abadi terhadap bahaya otoritarianisme dan manipulasi informasi.",
                R.drawable.nineteen_eighty_four));

        list.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald", "1925",
                Arrays.asList("Romance", "Drama", "Social", "Classic"),
                "Novel klasik ini merefleksikan kehidupan masyarakat Amerika pada era Jazz Age melalui kisah Jay Gatsby dan obsesinya terhadap cinta masa lalu. Di balik kemewahan dan pesta yang gemerlap, tersimpan kritik tajam terhadap ilusi American Dream dan kehampaan materialisme. Karya ini terus dikenang sebagai potret tragis ambisi, cinta, dan realitas sosial yang rapuh.",
                R.drawable.the_great_gatsby));

        list.add(new Book(4, "The Alchemist", "Paulo Coelho", "1988",
                Arrays.asList("Adventure", "Philosophy", "Fantasy"),
                "Karya modern yang telah memperoleh status klasik ini mengisahkan perjalanan spiritual seorang penggembala muda dalam mencari makna hidup dan takdirnya. Melalui simbolisme dan refleksi filosofis, novel ini menekankan pentingnya keberanian untuk mengikuti impian dan mendengarkan suara hati. Pesan universalnya menjadikan karya ini tetap relevan lintas generasi.",
                R.drawable.the_alchemist));

        list.add(new Book(5, "Catch 22", "Joseph Heller", "1961",
                Arrays.asList("Politics", "Strategy", "Leadership", "Philosophy"),
                "Sebagai karya satir klasik tentang perang, novel ini mengangkat absurditas birokrasi militer melalui konsep \u201cCatch-22\u201d yang paradoksal. Tokoh utamanya terjebak dalam sistem yang tidak logis, mencerminkan ironi dan kekacauan dalam struktur kekuasaan. Karya ini diakui luas sebagai kritik tajam terhadap institusi dan logika yang menindas individu.",
                R.drawable.catch_22));

        list.add(new Book(6, "Jane Eyre", "Charlotte Bront\u00eb", "1847",
                Arrays.asList("Romance", "Drama", "Social", "Classic"),
                "Sebagai salah satu karya klasik sastra Inggris, novel ini mengikuti perjalanan hidup Jane Eyre, seorang wanita yang berjuang mempertahankan integritas dan kemandiriannya di tengah berbagai tantangan. Kisah cintanya dengan Mr. Rochester disertai konflik moral dan emosional yang kompleks. Karya ini tetap dihargai sebagai representasi kekuatan karakter dan pencarian jati diri.",
                R.drawable.jane_eyre));

        list.add(new Book(7, "The Road", "Cormac McCarthy", "2006",
                Arrays.asList("Dystopian", "Political", "Sci-Fi", "Thriller"),
                "Karya kontemporer yang telah diakui sebagai klasik modern ini menggambarkan perjalanan seorang ayah dan anak di dunia pasca-apokaliptik yang suram. Di tengah kehancuran dan keputusasaan, hubungan mereka menjadi simbol harapan dan kemanusiaan yang bertahan. Novel ini menghadirkan refleksi mendalam tentang cinta, moralitas, dan arti bertahan hidup.",
                R.drawable.the_road));

        list.add(new Book(8, "Little Women", "Louisa May Alcott", "1868",
                Arrays.asList("Romance", "Drama", "Social", "Classic"),
                "Novel klasik ini mengisahkan kehidupan empat saudari March yang tumbuh dalam keterbatasan namun kaya akan nilai moral dan kasih sayang. Melalui perjalanan mereka menuju kedewasaan, karya ini mengeksplorasi tema keluarga, pengorbanan, dan identitas diri. Hingga kini, kisahnya tetap menginspirasi sebagai representasi kehangatan dan keteguhan dalam kehidupan.",
                R.drawable.little_women));

        list.add(new Book(9, "Invisible Man", "Ralph Ellison", "1952",
                Arrays.asList("Politics", "Strategy", "Leadership", "Philosophy"),
                "Novel klasik ini mengangkat isu identitas dan diskriminasi rasial melalui pengalaman seorang pria kulit hitam yang merasa \u201ctidak terlihat\u201d oleh masyarakat. Dengan pendekatan simbolis dan naratif yang kuat, karya ini mengeksplorasi perjuangan individu dalam mencari pengakuan dan makna diri. Hingga kini, novel ini dianggap sebagai karya penting dalam sastra Amerika.",
                R.drawable.invisible_man));

        list.add(new Book(10, "The Stranger", "Albert Camus", "1942",
                Arrays.asList("Dystopian", "Political", "Sci-Fi", "Thriller"),
                "Karya klasik eksistensial ini menggambarkan kehidupan Meursault, seorang individu yang tampak terasing dari norma sosial dan emosi konvensional. Melalui peristiwa sederhana yang berujung pada konsekuensi besar, novel ini mengeksplorasi absurditas kehidupan dan makna eksistensi manusia. Karya ini tetap relevan sebagai refleksi filosofis yang mendalam.",
                R.drawable.the_stranger));

        list.add(new Book(11, "The Count of Monte Cristo", "Alexandre Dumas", "1844",
                Arrays.asList("Romance", "Drama", "Social", "Classic"),
                "Novel klasik ini mengisahkan Edmond Dant\u00e8s yang dikhianati dan dipenjara secara tidak adil, kemudian bangkit dengan identitas baru untuk membalas dendam. Melalui alur yang penuh intrik dan strategi, karya ini mengeksplorasi tema keadilan, pengkhianatan, dan penebusan. Hingga kini, novel ini dianggap sebagai salah satu kisah petualangan paling epik dalam sastra dunia.",
                R.drawable.the_count_of_monte_cristo));

        list.add(new Book(12, "Feminine Mystique", "Betty Friedan", "1963",
                Arrays.asList("Feminism", "Society", "Politics", "Culture"),
                "Karya ini menjadi tonggak penting dalam gerakan feminisme modern dengan mengungkap ketidakpuasan perempuan terhadap peran domestik yang dibatasi. Friedan mengkritik norma sosial yang membatasi potensi perempuan dalam masyarakat. Hingga kini, buku ini diakui sebagai karya berpengaruh dalam studi gender dan sosial.",
                R.drawable.feminine_mystique));

        list.add(new Book(13, "The 7 Habits of Highly Effective People", "Stephen R. Covey", "1989",
                Arrays.asList("Self-help", "Leadership", "Productivity", "Growth"),
                "Sebagai salah satu karya klasik dalam pengembangan diri, buku ini menawarkan prinsip-prinsip fundamental untuk mencapai efektivitas pribadi dan profesional. Covey menekankan pentingnya karakter, kebiasaan, dan nilai dalam membangun kehidupan yang seimbang. Hingga kini, buku ini tetap menjadi referensi utama dalam dunia self-improvement.",
                R.drawable.the_7_habits));

        list.add(new Book(14, "Sapiens: The Birth of Humankind", "Yuval Noah Harari", "2011",
                Arrays.asList("History", "Anthropology", "Society", "Evolution"),
                "Karya modern yang telah memperoleh pengakuan luas ini menelusuri sejarah umat manusia dari masa prasejarah hingga era modern. Harari menggabungkan perspektif sejarah, biologi, dan antropologi untuk menjelaskan bagaimana manusia membentuk peradaban. Buku ini dikenal sebagai refleksi komprehensif tentang identitas dan perkembangan manusia.",
                R.drawable.sapiens));

        list.add(new Book(15, "A Brief History of Time", "Stephen Hawking", "1988",
                Arrays.asList("Science", "Physics", "Cosmology", "Education"),
                "Karya ilmiah klasik ini menjelaskan konsep-konsep kompleks dalam kosmologi, seperti waktu, ruang, dan asal-usul alam semesta, dengan bahasa yang dapat diakses oleh pembaca umum. Hawking membawa pembaca pada perjalanan intelektual untuk memahami hukum-hukum dasar alam. Buku ini menjadi salah satu karya populer dalam sains modern.",
                R.drawable.a_brief_history_of_time));

        list.add(new Book(16, "The Power of Myth", "Bill Moyers", "1988",
                Arrays.asList("Mythology", "Philosophy", "Culture", "Storytelling"),
                "Karya ini mengeksplorasi peran mitologi dalam membentuk budaya dan pemahaman manusia tentang kehidupan. Campbell menghubungkan berbagai kisah mitos dari seluruh dunia dengan pengalaman manusia yang universal. Buku ini tetap dihargai sebagai refleksi mendalam tentang makna dan simbol dalam kehidupan.",
                R.drawable.the_power_of_myth));

        list.add(new Book(17, "Beyond Good and Evil", "Friedrich Nietzsche", "1886",
                Arrays.asList("Philosophy", "Ethics", "Critique", "Psychology"),
                "Karya filsafat klasik ini menantang konsep moralitas tradisional dengan mengajak pembaca untuk berpikir melampaui dikotomi baik dan buruk. Nietzsche mengkritik nilai-nilai yang diterima secara umum dan mendorong pembentukan perspektif yang lebih bebas dan individual. Buku ini tetap menjadi karya penting dalam pemikiran filsafat modern.",
                R.drawable.beyond_good_and_evil));

        list.add(new Book(18, "The Art of Loving", "Erich Fromm", "1956",
                Arrays.asList("Psychology", "Love", "Philosophy", "Relationships"),
                "Dalam karya klasik ini, Fromm memandang cinta bukan sebagai perasaan semata, melainkan sebagai keterampilan yang perlu dipelajari dan dikembangkan. Ia menguraikan berbagai bentuk cinta serta tantangan dalam menjalin hubungan yang sehat. Buku ini tetap relevan sebagai panduan reflektif tentang hubungan antarmanusia.",
                R.drawable.the_art_of_loving));

        list.add(new Book(19, "Walden", "Henry David Thoreau", "1854",
                Arrays.asList("Philosophy", "Nature", "Minimalism", "Reflection"),
                "Dalam karya klasik ini, Thoreau merefleksikan kehidupan sederhana yang dijalaninya di alam sebagai bentuk pencarian makna dan kebebasan. Ia menekankan pentingnya kesederhanaan, kemandirian, dan hubungan dengan alam. Buku ini tetap relevan sebagai kritik terhadap materialisme dan gaya hidup modern.",
                R.drawable.walden));

        list.add(new Book(20, "Silent Spring", "Rachel Carson", "1962",
                Arrays.asList("Environment", "Science", "Activism", "Ecology"),
                "Karya klasik ini mengungkap dampak berbahaya penggunaan pestisida terhadap lingkungan dan ekosistem. Dengan pendekatan ilmiah yang kuat, buku ini membangkitkan kesadaran global akan pentingnya pelestarian alam. Kontribusinya menjadikannya sebagai tonggak penting dalam gerakan lingkungan modern.",
                R.drawable.silent_spring));

        list.add(new Book(21, "The Prince", "Niccolo Machiavelli", "1532",
                Arrays.asList("Politics", "Strategy", "Leadership", "Philosophy"),
                "Karya klasik dalam bidang politik ini membahas strategi kekuasaan dan kepemimpinan yang realistis, bahkan kontroversial. Machiavelli menekankan pentingnya pragmatisme dalam mempertahankan kekuasaan, terlepas dari pertimbangan moral tradisional. Hingga kini, buku ini tetap menjadi referensi penting dalam memahami dinamika politik dan kekuasaan.",
                R.drawable.the_prince));

        list.add(new Book(22, "Atomic Habits", "James Clear", "2018",
                Arrays.asList("Self-help", "Habit", "Productivity", "Psychology"),
                "Karya modern yang berpengaruh ini menguraikan bagaimana perubahan kecil yang konsisten dapat menghasilkan dampak besar dalam jangka panjang. Dengan pendekatan berbasis sains perilaku, buku ini memberikan strategi praktis untuk membangun kebiasaan baik dan menghilangkan kebiasaan buruk. Relevansinya menjadikannya salah satu karya penting dalam pengembangan diri masa kini.",
                R.drawable.atomic_habits));

        return list;
    }
}

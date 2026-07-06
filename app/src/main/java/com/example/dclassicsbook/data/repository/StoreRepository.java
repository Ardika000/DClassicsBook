package com.example.dclassicsbook.data.repository;

import com.example.dclassicsbook.R;
import com.example.dclassicsbook.data.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreRepository {

    public static List<Store> getStores() {
        List<Store> list = new ArrayList<>();
        list.add(new Store(1, "Forest Serenity", "Jl. Hutan Pinus No. 9, Kota Rembulan",
                R.drawable.store_forest_serenity));
        list.add(new Store(2, "Ocean Voyage", "Jl. Pelabuhan Lama No. 3, Kota Bahari",
                R.drawable.store_ocean_voyage));
        list.add(new Store(3, "Cosmic Nexus", "Jl. Angkasa Raya No. 17, Kota Orion",
                R.drawable.store_cosmic_nexus));
        list.add(new Store(4, "Sunset Bloom", "Jl. Melati Senja No. 45A, Kota Asmaraloka",
                R.drawable.store_sunset_bloom));
        list.add(new Store(5, "Noble Heights", "Jl. Bukit Mulia No. 12, Kota Adiwangsa",
                R.drawable.store_noble_heights));
        list.add(new Store(6, "Urban Pulse", "Jl. Metro Sentral No. 8, Kota Niaga",
                R.drawable.store_urban_pulse));
        return list;
    }
}

package net.atilist.harderthanwolves.item;

import net.modificationstation.stationapi.api.util.Identifier;

public class FilledCageItem extends LazyItemTemplate {
    public FilledCageItem(Identifier identifier) {
        super(identifier);
        setMaxCount(1);
    }
}

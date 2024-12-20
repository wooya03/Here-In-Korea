package kr.kro.hereinkorea.domain.hotels.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRoomEntity is a Querydsl query type for RoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRoomEntity extends EntityPathBase<RoomEntity> {

    private static final long serialVersionUID = 1534492126L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRoomEntity roomEntity = new QRoomEntity("roomEntity");

    public final QHotelsEntity hotels;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roomaircondition = createString("roomaircondition");

    public final NumberPath<Integer> roombasecount = createNumber("roombasecount", Integer.class);

    public final StringPath roombath = createString("roombath");

    public final StringPath roombathfacility = createString("roombathfacility");

    public final StringPath roomcable = createString("roomcable");

    public final StringPath roomcook = createString("roomcook");

    public final NumberPath<Integer> roomcount = createNumber("roomcount", Integer.class);

    public final StringPath roomhairdryer = createString("roomhairdryer");

    public final StringPath roomhometheater = createString("roomhometheater");

    public final StringPath roomimg1 = createString("roomimg1");

    public final StringPath roomimg2 = createString("roomimg2");

    public final StringPath roomimg3 = createString("roomimg3");

    public final StringPath roomimg4 = createString("roomimg4");

    public final StringPath roomimg5 = createString("roomimg5");

    public final StringPath roominternet = createString("roominternet");

    public final StringPath roomintro = createString("roomintro");

    public final NumberPath<Integer> roommaxcount = createNumber("roommaxcount", Integer.class);

    public final NumberPath<Integer> roomoffseasonminfee1 = createNumber("roomoffseasonminfee1", Integer.class);

    public final NumberPath<Integer> roomoffseasonminfee2 = createNumber("roomoffseasonminfee2", Integer.class);

    public final StringPath roompc = createString("roompc");

    public final StringPath roomrefrigerator = createString("roomrefrigerator");

    public final NumberPath<Integer> roomsize1 = createNumber("roomsize1", Integer.class);

    public final StringPath roomsofa = createString("roomsofa");

    public final StringPath roomtable = createString("roomtable");

    public final StringPath roomtitle = createString("roomtitle");

    public final StringPath roomtoiletries = createString("roomtoiletries");

    public final StringPath roomtv = createString("roomtv");

    public QRoomEntity(String variable) {
        this(RoomEntity.class, forVariable(variable), INITS);
    }

    public QRoomEntity(Path<? extends RoomEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRoomEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRoomEntity(PathMetadata metadata, PathInits inits) {
        this(RoomEntity.class, metadata, inits);
    }

    public QRoomEntity(Class<? extends RoomEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.hotels = inits.isInitialized("hotels") ? new QHotelsEntity(forProperty("hotels")) : null;
    }

}


package kr.kro.hereinkorea.domain.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalDetailsEntity is a Querydsl query type for FestivalDetailsEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalDetailsEntity extends EntityPathBase<FestivalDetailsEntity> {

    private static final long serialVersionUID = 1471260756L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestivalDetailsEntity festivalDetailsEntity = new QFestivalDetailsEntity("festivalDetailsEntity");

    public final StringPath eventenddate = createString("eventenddate");

    public final StringPath eventplace = createString("eventplace");

    public final StringPath eventstartdate = createString("eventstartdate");

    public final QFestivalEntity festival;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath overview = createString("overview");

    public final StringPath playtime = createString("playtime");

    public final StringPath sponsor1 = createString("sponsor1");

    public final StringPath sponsor1tel = createString("sponsor1tel");

    public final StringPath usetimefestival = createString("usetimefestival");

    public QFestivalDetailsEntity(String variable) {
        this(FestivalDetailsEntity.class, forVariable(variable), INITS);
    }

    public QFestivalDetailsEntity(Path<? extends FestivalDetailsEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestivalDetailsEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestivalDetailsEntity(PathMetadata metadata, PathInits inits) {
        this(FestivalDetailsEntity.class, metadata, inits);
    }

    public QFestivalDetailsEntity(Class<? extends FestivalDetailsEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.festival = inits.isInitialized("festival") ? new QFestivalEntity(forProperty("festival")) : null;
    }

}


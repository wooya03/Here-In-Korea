package kr.kro.hereinkorea.domain.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalEntity is a Querydsl query type for FestivalEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalEntity extends EntityPathBase<FestivalEntity> {

    private static final long serialVersionUID = 468031284L;

    public static final QFestivalEntity festivalEntity = new QFestivalEntity("festivalEntity");

    public final StringPath addr1 = createString("addr1");

    public final StringPath addr2 = createString("addr2");

    public final NumberPath<Integer> areacode = createNumber("areacode", Integer.class);

    public final NumberPath<Long> contentId = createNumber("contentId", Long.class);

    public final NumberPath<Integer> contentTypeId = createNumber("contentTypeId", Integer.class);

    public final DatePath<java.time.LocalDate> eventEndDate = createDate("eventEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> eventStartDate = createDate("eventStartDate", java.time.LocalDate.class);

    public final ListPath<FestivalDetailsEntity, QFestivalDetailsEntity> festivalDetailsEntities = this.<FestivalDetailsEntity, QFestivalDetailsEntity>createList("festivalDetailsEntities", FestivalDetailsEntity.class, QFestivalDetailsEntity.class, PathInits.DIRECT2);

    public final NumberPath<Double> mapx = createNumber("mapx", Double.class);

    public final NumberPath<Double> mapy = createNumber("mapy", Double.class);

    public final StringPath tel = createString("tel");

    public final StringPath title = createString("title");

    public QFestivalEntity(String variable) {
        super(FestivalEntity.class, forVariable(variable));
    }

    public QFestivalEntity(Path<? extends FestivalEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFestivalEntity(PathMetadata metadata) {
        super(FestivalEntity.class, metadata);
    }

}


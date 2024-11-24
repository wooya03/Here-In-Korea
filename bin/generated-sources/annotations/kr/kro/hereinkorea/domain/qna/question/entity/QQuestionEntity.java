package kr.kro.hereinkorea.domain.qna.question.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionEntity is a Querydsl query type for QuestionEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionEntity extends EntityPathBase<QuestionEntity> {

    private static final long serialVersionUID = 1311359578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionEntity questionEntity = new QQuestionEntity("questionEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final kr.kro.hereinkorea.domain.member.Entity.QMemberEntity member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath qCategory = createString("qCategory");

    public final StringPath qContents = createString("qContents");

    public final NumberPath<Long> qId = createNumber("qId", Long.class);

    public final BooleanPath qStatus = createBoolean("qStatus");

    public final StringPath qTitle = createString("qTitle");

    public QQuestionEntity(String variable) {
        this(QuestionEntity.class, forVariable(variable), INITS);
    }

    public QQuestionEntity(Path<? extends QuestionEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionEntity(PathMetadata metadata, PathInits inits) {
        this(QuestionEntity.class, metadata, inits);
    }

    public QQuestionEntity(Class<? extends QuestionEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new kr.kro.hereinkorea.domain.member.Entity.QMemberEntity(forProperty("member")) : null;
    }

}


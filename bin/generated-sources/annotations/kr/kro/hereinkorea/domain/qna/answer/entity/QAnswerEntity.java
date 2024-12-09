package kr.kro.hereinkorea.domain.qna.answer.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswerEntity is a Querydsl query type for AnswerEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswerEntity extends EntityPathBase<AnswerEntity> {

    private static final long serialVersionUID = 1476630474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswerEntity answerEntity = new QAnswerEntity("answerEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    public final StringPath contents = createString("contents");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final kr.kro.hereinkorea.domain.member.Entity.QMemberEntity member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final kr.kro.hereinkorea.domain.qna.question.entity.QQuestionEntity question;

    public QAnswerEntity(String variable) {
        this(AnswerEntity.class, forVariable(variable), INITS);
    }

    public QAnswerEntity(Path<? extends AnswerEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswerEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswerEntity(PathMetadata metadata, PathInits inits) {
        this(AnswerEntity.class, metadata, inits);
    }

    public QAnswerEntity(Class<? extends AnswerEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new kr.kro.hereinkorea.domain.member.Entity.QMemberEntity(forProperty("member")) : null;
        this.question = inits.isInitialized("question") ? new kr.kro.hereinkorea.domain.qna.question.entity.QQuestionEntity(forProperty("question"), inits.get("question")) : null;
    }

}


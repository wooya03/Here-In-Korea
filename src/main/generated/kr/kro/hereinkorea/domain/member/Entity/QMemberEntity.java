package kr.kro.hereinkorea.domain.member.Entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 1172524568L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final kr.kro.hereinkorea.global.entity.QBaseEntity _super = new kr.kro.hereinkorea.global.entity.QBaseEntity(this);

    public final StringPath birth = createString("birth");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final StringPath gender = createString("gender");

    public final DateTimePath<java.util.Date> loginDate = createDateTime("loginDate", java.util.Date.class);

    public final StringPath memId = createString("memId");

    public final StringPath memName = createString("memName");

    public final StringPath memPass = createString("memPass");

    public final StringPath memTel = createString("memTel");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final DateTimePath<java.util.Date> signDate = createDateTime("signDate", java.util.Date.class);

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}


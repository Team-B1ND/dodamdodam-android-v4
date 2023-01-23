package kr.hs.dgsw.smartschool.data.network.url

object DodamUrl {
    const val AUTH = "auth"
    const val BANNER = "banner"
    const val BUS = "bus"
    const val CLASSROOM = "classroom"
    const val UPLOAD = "upload"
    const val ITMAP = "itmap"
    const val LOST_FOUND = "lostfound"
    const val MEAL = "meal"
    const val MEMBER = "members"
    const val OUT = "out"
    const val PLACE = "place"
    const val POINT = "point"
    const val WAKE_UP_SONG = "wakeup-song"
    const val STUDY_ROOM = "study-room"
    const val TIME = "time"
    const val TOKEN = "token"
    const val SEARCH = "search"

    object Auth {
        const val LOGIN = "$AUTH/login"
        const val JOIN = "$AUTH/join-student"
    }

    object Banner {
        const val ACTIVE = "$BANNER/active"
    }

    object Bus {
        const val APPLY = "$BUS/apply"
        const val ID = "$BUS/{id}"

        object Apply {
            const val MONTH = "$APPLY/month"
            const val BUS_ID = "$APPLY/{busId}"
        }
    }

    object Itmap {
        const val COMPANIES = "$ITMAP/companies"
        const val COMPANY = "$ITMAP/company"

        object Company {
            const val ID = "$COMPANY/{id}"
        }
    }

    object LostFound {
        const val COMMENT = "$LOST_FOUND/comment"
        const val SEARCH = "$LOST_FOUND/search"
        const val ALL = "$LOST_FOUND/all"
        const val MY = "$LOST_FOUND/my"
        const val ID = "$LOST_FOUND/{id}"

        object Comment {
            const val ID = "$COMMENT/{id}"
        }
    }

    object Meal {
        const val CALORIE = "$MEAL/calorie"
    }

    object Member {
        const val MEMBER_ID = "$MEMBER/{memberId}"
        const val MY = "$MEMBER/my"
        const val PARENT = "$MEMBER/parent"
        const val PW = "$MEMBER/pw"
        const val SEARCH = "$MEMBER/search"
        const val STUDENT = "$MEMBER/student"
        const val TEACHER = "$MEMBER/teacher"

        object Search {
            const val MEMBER_ID = "$SEARCH/{memberId}"
        }
    }

    object Out {
        const val OUT_SLEEPING = "$OUT/outsleeping"
        const val OUT_GOING = "$OUT/outgoing"

        object OutSleeping {
            const val ID = "$OUT_SLEEPING/{id}"
            const val MY = "$OUT_SLEEPING/my"
            const val OUT_SLEEPING_ID = "$OUT_SLEEPING/{outsleepingId}"
        }

        object OutGoing {
            const val ID = "$OUT_GOING/{id}"
            const val MY = "$OUT_GOING/my"
            const val OUT_GOING_ID = "$OUT_GOING/{outgoingId}"
        }
    }

    object Point {
        const val MY = "$POINT/my"

        object My {
            const val YEAR = "$MY/year"
        }
    }

    object WakeUpSong {
        const val MY = "$WAKE_UP_SONG/my"
        const val ALLOWED = "$WAKE_UP_SONG/allowed"
        const val PENDING = "$WAKE_UP_SONG/pending"
        const val CHART = "$WAKE_UP_SONG/chart"

        object My {
            const val ID = "$MY/{id}"
        }
    }

    object StudyRoom {
        const val ID = "$STUDY_ROOM/{id}"
        const val DEFAULT = "$STUDY_ROOM/default"
        const val MY = "$STUDY_ROOM/my"

        object Default {
            const val WEEK_TYPE = "$DEFAULT/week-type"
        }
    }

    object Time {
        const val TABLES = "$TIME/tables"
    }

    object ClassRoom {
        const val GET_CLASSROOM = "$CLASSROOM/"
    }

    object Token {
        const val REFRESH = "$TOKEN/refresh"
    }
}

package org.ruixun.yupoo.bean;

import javax.persistence.*;

@Entity
@Table(name = "capacity")
/*剩余容量 单位B */
public class SurplusCapacity {/*剩余容量类  */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;/*用户id*/
    @Column(name = "surplus_capacity")
    private Long surplusCapacity;/*剩余容量*/

    @Override
    public String toString() {
        return "SurplusCapacity{" +
                "id=" + id +
                ", uid=" + uid +
                ", surplusCapacity=" + surplusCapacity +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getSurplusCapacity() {
        return surplusCapacity;
    }

    public void setSurplusCapacity(Long surplusCapacity) {
        this.surplusCapacity = surplusCapacity;
    }

    public SurplusCapacity() {
    }

    public SurplusCapacity(Long uid, Long surplusCapacity) {
        this.uid = uid;
        this.surplusCapacity = surplusCapacity;
    }
}

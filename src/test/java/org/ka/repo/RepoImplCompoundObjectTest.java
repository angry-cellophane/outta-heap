package org.ka.repo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.ka.ref.Ref;

import static org.junit.Assert.*;

public class RepoImplCompoundObjectTest {

    private static class CompoundObject1 {
        private int i;
        private Integer iO;

        private double d;
        private Double dO;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public Integer getiO() {
            return iO;
        }

        public void setiO(Integer iO) {
            this.iO = iO;
        }

        public double getD() {
            return d;
        }

        public void setD(double d) {
            this.d = d;
        }

        public Double getdO() {
            return dO;
        }

        public void setdO(Double dO) {
            this.dO = dO;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CompoundObject1 that = (CompoundObject1) o;

            if (i != that.i) return false;
            if (Double.compare(that.d, d) != 0) return false;
            if (iO != null ? !iO.equals(that.iO) : that.iO != null) return false;
            return !(dO != null ? !dO.equals(that.dO) : that.dO != null);

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = i;
            result = 31 * result + (iO != null ? iO.hashCode() : 0);
            temp = Double.doubleToLongBits(d);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (dO != null ? dO.hashCode() : 0);
            return result;
        }
    }

    private static class CompoundObject2 {
        private int i;
        private Integer iO;

        private double d;
        private Double dO;

        private CompoundObject1 cO;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public Integer getiO() {
            return iO;
        }

        public void setiO(Integer iO) {
            this.iO = iO;
        }

        public double getD() {
            return d;
        }

        public void setD(double d) {
            this.d = d;
        }

        public Double getdO() {
            return dO;
        }

        public void setdO(Double dO) {
            this.dO = dO;
        }

        public CompoundObject1 getcO() {
            return cO;
        }

        public void setcO(CompoundObject1 cO) {
            this.cO = cO;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CompoundObject2 that = (CompoundObject2) o;

            if (i != that.i) return false;
            if (Double.compare(that.d, d) != 0) return false;
            if (iO != null ? !iO.equals(that.iO) : that.iO != null) return false;
            if (dO != null ? !dO.equals(that.dO) : that.dO != null) return false;
            return !(cO != null ? !cO.equals(that.cO) : that.cO != null);

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = i;
            result = 31 * result + (iO != null ? iO.hashCode() : 0);
            temp = Double.doubleToLongBits(d);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (dO != null ? dO.hashCode() : 0);
            result = 31 * result + (cO != null ? cO.hashCode() : 0);
            return result;
        }
    }

    private RepoImpl repo;

    @Before public void setup() {
        this.repo = new RepoImpl(200L);
    }

    @After public void teardown() {
        if (repo != null) repo.clean();
    }

    @Test public void testCompoundObject1() {
        CompoundObject1 c1 = newCompoundObject1();

        Ref<CompoundObject1> ref = repo.put(c1);
        CompoundObject1 c2 = repo.get(ref);

        assertEquals(c1, c2);
    }

    @Test public void testCompoundObject2() {
        CompoundObject1 c1 = newCompoundObject1();
        CompoundObject2 co2Expected = newCompoundObject2(c1);

        Ref<CompoundObject2> ref = repo.put(co2Expected);
        CompoundObject2 co2Actual = repo.get(ref);

        assertFalse(co2Expected == co2Actual);
        assertFalse(co2Expected.getcO() == co2Actual.getcO());
        assertEquals(co2Expected, co2Actual);
    }

    private CompoundObject2 newCompoundObject2(CompoundObject1 c1) {
        CompoundObject2 c2 = new CompoundObject2();
        c2.setI(1);
        c2.setiO(1);
        c2.setD(1.0);
        c2.setdO(1.0);
        c2.setcO(c1);

        return c2;
    }

    private CompoundObject1 newCompoundObject1() {
        CompoundObject1 c1 = new CompoundObject1();
        c1.setD(1.0);
        c1.setdO(2.0);
        c1.setI(1);
        c1.setiO(1);
        return c1;
    }

}
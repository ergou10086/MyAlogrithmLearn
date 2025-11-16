-- 判断三个线段能否构成三角形的条件是：任意两边之和大于第三边。
-- 也就是说，对于三个边长 x, y, z，需要满足
-- x + y > z
-- x + z > y
-- y + z > x

SELECT x, y, z,
       CASE
            WHEN x <= ABS(y - z) OR y <= ABS(x - z) OR z <= ABS(x - y) THEN 'No'
            ELSE 'Yes'
       END AS triangle
FROM Triangle;
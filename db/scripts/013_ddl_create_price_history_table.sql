CREATE TABLE price_history
(
    id      INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    before  BIGINT not null,
    after   BIGINT not null,
    created TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
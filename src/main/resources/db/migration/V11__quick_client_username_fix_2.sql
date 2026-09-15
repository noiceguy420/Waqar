ALTER TABLE client
    ADD CONSTRAINT noEmailSymbol
        CHECK (INSTR(client.Username, '@') = 0);